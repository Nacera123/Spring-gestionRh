package getionRh.example.rh.service;


import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.*;
import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.entity.candidature.EtatCandidature;
import getionRh.example.rh.entity.candidature.PosteVacant;
import getionRh.example.rh.repository.*;
import getionRh.example.rh.repository.candidature.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class monDocTest {

    @Value("${file.upload.path}")
    private String pathUploadFile;

    @Autowired
    private DocumentCandidatureRepository docCandidatureRepository;

    @Autowired
    private PosteVacantRepository posteVacantRepository;

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private CiviliteRepository civiliteRepository;

    @Autowired
    private IndividuRepository individuRepository;

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private EtatCandidatureRepository etatCandidatureRepository;

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private NomDocumentRepository nomDocumentRepository;

    @Autowired
    private CandidatsReository candidatsReository;


    private String uploadFile(MultipartFile file)throws Exception{

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String pathForDate = dateFormat.format(new java.util.Date());
        File directory = new File(this.pathUploadFile + "/" + pathForDate);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        int positionPoint = fileName.lastIndexOf(".");
        String extension = fileName.substring(positionPoint); // ".png"
        List<String> extAutoriser = List.of(".png", ".jpg", ".webp", ".svg", ".jpeg", ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx");

        if (!extAutoriser.contains(extension.toLowerCase())) { // toLowerCase() => minuscule
            throw new Exception("Extension non autorisée");
        }

        // Générer un nom de fichier unique
        String uniqueId = UUID.randomUUID().toString();
        fileName = uniqueId + extension;
        File newFile = new File(directory.getAbsolutePath(), fileName);
        file.transferTo(newFile);

        return "/" + pathForDate + fileName;

    }

    public DocumentCandidature saveCandidature(MultipartFile fileCv, MultipartFile fileLm, String nomFileCV, String nomFileLM, IndividuDto individuDto, Integer postId) throws Exception {

        // Récupérer le poste vacant
        PosteVacant post = posteVacantRepository.findById(postId) .orElseThrow(() -> new Exception("Poste vacant non trouvé"));

        // recuperer le pays
        Pays nomPays = paysRepository.findById(individuDto.getPays().getId()).orElse(null);

        //recuperer la civilite
        Civilite civilite = civiliteRepository.findById(individuDto.getCivilite().getId()).orElse(null);

        // creer individu
        Individu individu = new Individu();
        individu.setNom(individuDto.getNom());
        individu.setPrenom(individuDto.getPrenom());
        individu.setTelephone(individuDto.getTelephone());
        individu.setEmail(individuDto.getEmail());
        individu.setPays(nomPays);
        individu.setCivilite(civilite);
        individuRepository.save(individu);

        //creer candidat
        Candidats candidat = new Candidats();
        candidat.setIndividu(individu);
        candidatsReository.save(candidat);



        //recuperer etat de la candidature
        EtatCandidature etatCandidature = etatCandidatureRepository.findByEtatIgnoreCase("Candidature envoyée");



        // creer candidature
        Candidature candidature = new Candidature();
        candidature.setPosteVacant(post);
        candidature.setIndividu(individu);
        candidature.setEtatCandidature(etatCandidature);
        candidatureRepository.save(candidature);



        //Enregistrer le Doc1 : CV
        DocumentCandidature docCandidature = new DocumentCandidature();
        docCandidature.setNomFichier(this.uploadFile(fileCv));
        docCandidature.setCandidature(candidature);
        docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileCV));
        docCandidatureRepository.save(docCandidature);

        //Enregistrer le Doc1 : CV
        docCandidature = new DocumentCandidature();
        docCandidature.setNomFichier(this.uploadFile(fileLm));
        docCandidature.setCandidature(candidature);
        docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileLM));
        docCandidatureRepository.save(docCandidature);

        return docCandidatureRepository.save(docCandidature);



    }
}
