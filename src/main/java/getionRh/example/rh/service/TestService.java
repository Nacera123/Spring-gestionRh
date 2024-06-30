package getionRh.example.rh.service;

import getionRh.example.rh.repository.*;
import getionRh.example.rh.repository.candidature.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class TestService {

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

    @Value("${file.upload.path}")
    private String pathUploadFile;

    public String uploadFile(MultipartFile file) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String pathForDate = dateFormat.format(new java.util.Date());
        File directory = new File(this.pathUploadFile + "/" + pathForDate);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        int positionPoint = fileName.lastIndexOf(".");
        String extension = fileName.substring(positionPoint);
        List<String> extAutoriser = List.of(".pdf", ".txt");

        if (!extAutoriser.contains(extension.toLowerCase())) {
            throw new Exception("Extension non autorisée");
        }

        // Générer un nom de fichier unique
        String uniqueId = UUID.randomUUID().toString();
        fileName = uniqueId + extension;
        File newFile = new File(directory.getAbsolutePath(), fileName);
        file.transferTo(newFile);

        // return fileName;
        return "/" + pathForDate + fileName;

    }

    private String getNomFichier(String filePath) {

        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    public String getCheminCompletFichier(String filePath) {

        String HOME_PATH = "/DATA/DOC_RH";
        File f = new File(HOME_PATH);
        if (f.exists()) {
            System.err.println("Le répertoire :" + f.getAbsolutePath() + "| existe ");
        } else {
            f.mkdirs();
            System.err.println("Le repertoire :" + f.getAbsolutePath() + " | a été cree avec succès.");
        }
        String HOME_PATH2 = "/DATA/DOC_RH/";
        File f2 = new File(HOME_PATH2 + getNomFichier(filePath));
        return f2.getAbsolutePath();
    }

}
