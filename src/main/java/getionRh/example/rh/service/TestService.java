package getionRh.example.rh.service;


import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.*;
import getionRh.example.rh.entity.candidature.*;
import getionRh.example.rh.manager.TokenGenerater;
import getionRh.example.rh.repository.*;
import getionRh.example.rh.repository.candidature.*;
import getionRh.example.rh.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TestService {

    @Autowired
    private DocumentCandidatureRepository docCandidatureRepository;

    @Value("${file.upload.path}")
    private String pathUploadFile;

    @Autowired
    private PosteVacantRepository posteVacantRepository;

    @Autowired
    private CandidatureRepository candidatureRepository;


    @Autowired
    private NomDocumentRepository nomDocumentRepository;

    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private CiviliteRepository civiliteRepository;

    @Autowired
    private IndividuRepository individuRepository;

    @Autowired
    private EtatCandidatureRepository etatCandidatureRepository;


    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserServiceImpl userService;

    public DocumentCandidature saveUser(MultipartFile fileCv, MultipartFile fileLm, String nomFileCV, String nomFileLM, IndividuDto individuDto, Integer postId) throws Exception {


        // Récupérer le poste vacant
        PosteVacant post = posteVacantRepository.findById(postId) .orElseThrow(() -> new Exception("Poste vacant non trouvé"));

        Candidature candidature = new Candidature();
        candidature.setPosteVacant(post);



        /**Ajout civilite**/



        /**Ajout Individu**/
        Individu individu = new Individu();
        individu.setNom(individuDto.getNom());
        individu.setPrenom(individuDto.getPrenom());
        individu.setTelephone(individuDto.getTelephone());
        individu.setEmail(individuDto.getEmail());

        //pays
        Pays nomPays = paysRepository.findById(individuDto.getPays().getId()).orElse(null);
        individu.setPays(nomPays);
        //civilite
        Civilite civilite = civiliteRepository.findById(individuDto.getCivilite().getId()).orElse(null);
        individu.setCivilite(civilite);
        individuRepository.save(individu);

        /***Candidat*/
        Candidat candidat = new Candidat();
        candidat.setIndividu(individu);
        candidatRepository.save(candidat);
        /**fin candidat */

        /*** User **/
        User user = new User();
        user.setIndividu(individu);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role role = roleRepository.findByNom("CANDIDAT").orElse(null);
        user.setRoles(role);

        user.setToken(TokenGenerater.generateToken(userService));
        userService.save(user);

        candidature.setIndividu(individu);
        EtatCandidature etatCandidature = etatCandidatureRepository.findByEtatIgnoreCase("Candidature envoyée");
        candidature.setEtatCandidature(etatCandidature);

        /*****************************************/
        candidatureRepository.save(candidature);


        // Créer et enregistrer la nouvelle candidature
        // CV
        DocumentCandidature docCandidature = new DocumentCandidature();
        docCandidature.setNomFichier(this.uploadFile(fileCv));
        docCandidature.setCandidature(candidature);
        docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileCV));

        docCandidatureRepository.save(docCandidature);


        // LM

        docCandidature = new DocumentCandidature();
        docCandidature.setNomFichier(this.uploadFile(fileLm));
        docCandidature.setCandidature(candidature);
        docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileLM));

        docCandidatureRepository.save(docCandidature);

        // Assurez-vous que la candidature est initialisée avant d'essayer de définir son poste vacant




        return docCandidatureRepository.save(docCandidature);
    }







   public Map<String, String> save(MultipartFile fileCv, MultipartFile fileLm, String nomFileCV, String nomFileLM, IndividuDto individuDto, Integer postId) throws Exception {


        // Récupérer le poste vacant
        PosteVacant post = posteVacantRepository.findById(postId) .orElseThrow(() -> new Exception("Poste vacant non trouvé"));

        Candidature candidature = new Candidature();
        candidature.setPosteVacant(post);



       /**Ajout civilite**/



       /**Ajout Individu**/
       Individu individu = new Individu();
       individu.setNom(individuDto.getNom());
       individu.setPrenom(individuDto.getPrenom());
       individu.setTelephone(individuDto.getTelephone());
       individu.setEmail(individuDto.getEmail());
       //pays
       Pays nomPays = paysRepository.findById(individuDto.getPays().getId()).orElse(null);
       individu.setPays(nomPays);
       //civilite
       Civilite civilite = civiliteRepository.findById(individuDto.getCivilite().getId()).orElse(null);
       individu.setCivilite(civilite);
       individuRepository.save(individu);

       /***Candidat*/
       Candidat candidat = new Candidat();
       candidat.setIndividu(individu);
       candidatRepository.save(candidat);


        candidature.setIndividu(individu);
       EtatCandidature etatCandidature = etatCandidatureRepository.findByEtatIgnoreCase("Candidature envoyée");
       candidature.setEtatCandidature(etatCandidature);

       /*****************************************/
       candidatureRepository.save(candidature);


        // Créer et enregistrer la nouvelle candidature
        // CV
        DocumentCandidature docCandidature = new DocumentCandidature();
        docCandidature.setNomFichier(this.uploadFile(fileCv));
        docCandidature.setCandidature(candidature);
        docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileCV));

        docCandidatureRepository.save(docCandidature);


        // LM

       docCandidature = new DocumentCandidature();
       docCandidature.setNomFichier(this.uploadFile(fileLm));
       docCandidature.setCandidature(candidature);
       docCandidature.setNomPieceJointe(nomDocumentRepository.findByNomIgnoreCase(nomFileLM));

        docCandidatureRepository.save(docCandidature);

        // Assurez-vous que la candidature est initialisée avant d'essayer de définir son poste vacant


       docCandidatureRepository.save(docCandidature);

        return Map.of("idIndividu", ""+individu.getId());
    }

    /*
    upload le fichier et return le chemin c tout
     */
    private String uploadFile(MultipartFile file) throws Exception {
        // Format de la date pour le chemin du fichier
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String pathForDate = dateFormat.format(new java.util.Date());
        File directory = new File(this.pathUploadFile + "/" + pathForDate);

        // Vérifier et créer le répertoire si nécessaire
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Vérifier l'extension du fichier
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



}
