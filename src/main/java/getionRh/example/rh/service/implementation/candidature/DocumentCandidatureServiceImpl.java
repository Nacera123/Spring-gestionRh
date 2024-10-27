package getionRh.example.rh.service.implementation.candidature;

import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.Candidats;
import getionRh.example.rh.entity.Civilite;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.entity.candidature.EtatCandidature;
import getionRh.example.rh.entity.candidature.PosteVacant;
import getionRh.example.rh.exception.ResponeExtendException;
import getionRh.example.rh.repository.*;
import getionRh.example.rh.repository.candidature.*;
import getionRh.example.rh.service.candidature.DocumentCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentCandidatureServiceImpl implements DocumentCandidatureService {
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public DocumentCandidature getById(int id)throws ResponeExtendException {
        return docCandidatureRepository.findById(id).orElse(null);
    }

    public List<DocumentCandidature> getByCandidature(int id){
        return docCandidatureRepository.findAllByCandidatureId(id);
    }

    public List<Object[]> getAllByCandidature() {
        return docCandidatureRepository.findDocumentsGroupedByCandidatureId();
    }

    public List<DocumentCandidature> getAll() throws Exception {
        List<DocumentCandidature> documentCandidatures = docCandidatureRepository.findAll();
        if (documentCandidatures.isEmpty()) {
            throw new Exception("la liste des document est vide");
        } else {
            return documentCandidatures;
        }
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String pathForDate = dateFormat.format(new java.util.Date());
        File directory = new File(this.pathUploadFile);
        // File directory = new File(this.pathUploadFile + "/" + pathForDate);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        int positionPoint = fileName.lastIndexOf(".");
        String extension = fileName.substring(positionPoint);
        List<String> extAutoriser = List.of(".png", ".jpg", ".webp", ".svg", ".jpeg", ".pdf", ".doc", ".docx", ".xls",
                ".xlsx", ".ppt", ".pptx");

        if (!extAutoriser.contains(extension.toLowerCase())) {
            throw new Exception("Extension non autorisée");
        }

        // Générer un nom de fichier unique
        String uniqueId = UUID.randomUUID().toString();
        fileName = uniqueId + extension;
        File newFile = new File(directory.getAbsolutePath(), fileName);
        file.transferTo(newFile);

        return fileName;
        // return "/" + pathForDate + fileName;

    }

    @Override
    public Map<String, String> saveCandidature(MultipartFile fileCv, MultipartFile fileLm, String nomFileCV,
            String nomFileLM, IndividuDto individuDto, Integer postId) throws Exception {

        // Récupérer le poste vacant
        PosteVacant post = posteVacantRepository.findById(postId)
                .orElseThrow(() -> new Exception("Poste vacant non trouvé"));

        Candidature candidature = new Candidature();
        candidature.setPosteVacant(post);

        // recuperer le pays
        Pays nomPays = paysRepository.findById(individuDto.getPays().getId()).orElse(null);

        // recuperer la civilite
        Civilite civilite = civiliteRepository.findById(individuDto.getCivilite().getId()).orElse(null);

        Individu individu = individuRepository.findByEmail(individuDto.getEmail());
        // creer individu
        if (individu == null) {
            individu = new Individu();
            // Individu individu = new Individu();
            individu.setNom(individuDto.getNom());
            individu.setPrenom(individuDto.getPrenom());
            individu.setTelephone(individuDto.getTelephone());
            individu.setEmail(individuDto.getEmail());
            individu.setPays(nomPays);
            individu.setCivilite(civilite);
            individuRepository.save(individu);
        }

        // Vérifier si un candidat existe déjà pour cet individu
        Candidats candidat = candidatsReository.findByIndividu(individu);
        if (candidat == null) {
            // Créer un nouveau candidat
            candidat = new Candidats();
            candidat.setIndividu(individu);
            candidatsReository.save(candidat);
        }

        // creer candidat
        // Candidats candidat = new Candidats();
        // candidat.setIndividu(individu);
        // candidatsReository.save(candidat);

        candidature.setIndividu(individu);

        // recuperer etat de la candidature
        EtatCandidature etatCandidature = etatCandidatureRepository.findByEtatIgnoreCase("envoyée");
        // creer candidature

        candidature.setEtatCandidature(etatCandidature);
        candidatureRepository.save(candidature);

        // Enregistrer le Doc1 : CV
        DocumentCandidature docCandidature = new DocumentCandidature();
        docCandidature.setNomFichier(this.uploadFile(fileCv));
        docCandidature.setCandidature(candidature);
        docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileCV));
        docCandidatureRepository.save(docCandidature);

        // Enregistrer le Doc1 : CV
        docCandidature = new DocumentCandidature();
        docCandidature.setNomFichier(this.uploadFile(fileLm));
        docCandidature.setCandidature(candidature);
        docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileLM));
        docCandidatureRepository.save(docCandidature);

        docCandidatureRepository.save(docCandidature);

        return Map.of("idIndividu", "" + individu.getId());

    }

    /**
     * Méthode pour obtenir le nom du fichier
     */
    private String getNomFichier(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    public String getCheminCompletFichiers(String filePath) {
        String HOME_PATH = pathUploadFile;
        File f = new File(HOME_PATH);
        if (f.exists()) {
            System.err.println("Le répertoire :" + f.getAbsolutePath() + "| existe ");
        } else {
            f.mkdirs();
            System.err.println("Le repertoire :" + f.getAbsolutePath() + " | a été cree avec succès.");
        }
        String HOME_PATH2 = pathUploadFile;
        File f2 = new File(HOME_PATH2 + getNomFichier(filePath));
        return f2.getAbsolutePath();
    }

    // public String getCheminCompletFichier(String filePath) {

    // String HOME_PATH = "/DATA/DOC_RH";
    // File f = new File(HOME_PATH);
    // if (f.exists()) {
    // System.err.println("Le répertoire :" + f.getAbsolutePath() + "| existe ");
    // } else {
    // f.mkdirs();
    // System.err.println("Le repertoire :" + f.getAbsolutePath() + " | a été cree
    // avec succès.");
    // }
    // String HOME_PATH2 = "/DATA/DOC_RH/";
    // File f2 = new File(HOME_PATH2 + getNomFichier(filePath));
    // return f2.getAbsolutePath();
    // }

}
