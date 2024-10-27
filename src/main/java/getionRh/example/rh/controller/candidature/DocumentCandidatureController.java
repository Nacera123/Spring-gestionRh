package getionRh.example.rh.controller.candidature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.exception.ResponeExtendException;
import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.candidature.*;
import jakarta.annotation.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/document-candidature")
public class DocumentCandidatureController {

    @Value("${file.upload.path}")
    private String pathUploadFile;

    @Autowired
    private DocumentCandidatureServiceImpl documentCandidatureService;

    @Autowired
    private CandidatServiceImpl candidatRepository;

    @GetMapping("/list")
    public ResponseEntity<?> getAlldocument() {
        try {
            return ResponseEntity.ok(documentCandidatureService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPdf(@RequestParam("fileCV") MultipartFile fileCV,
            @RequestParam(value = "fileLM", required = false) MultipartFile fileLM,
            @RequestParam(value = "nomFileCV", required = false, defaultValue = "--") String nomFileCV,
            @RequestParam(value = "nomFileLM", required = false, defaultValue = "--") String nomFileLM,
            @RequestParam(value = "datas", required = false) String datas,
            @RequestParam("postId") Integer postId) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        IndividuDto individuDto = mapper.readValue(datas, IndividuDto.class);

        try {
            return ResponseEntity.ok(documentCandidatureService.saveCandidature(fileCV, fileLM, nomFileCV, nomFileLM,
                    individuDto, postId));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // @GetMapping("/download/{filePath:.+}")
    // public ResponseEntity<?> downloadFile(@PathVariable String filePath) {
    // try {
    // String file = documentCandidatureService.getCheminCompletFichier(filePath);

    // // Lire le contenu du fichier en utilisant Files.readAllBytes
    // // Path path = file.toPath();
    // // byte[] fileContent = Files.readAllBytes(path);

    // // Construire la réponse HTTP avec le fichier en tant que contenu
    // return ResponseEntity.ok(file);

    // } catch (Exception e) {
    // e.printStackTrace();
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Erreur lors du téléchargement du fichier : " + e.getMessage());
    // }
    // }

    // }
    @GetMapping("/toto/{filePath}")
    public String toto(@PathVariable String filePath) {

        String absolutePath = documentCandidatureService.getCheminCompletFichiers(filePath);
        return absolutePath;
    }

    @GetMapping("/toto/titi/{filePath}")
    public ResponseEntity<?> titi(@PathVariable String filePath) {

        String absolutePath = documentCandidatureService.getCheminCompletFichiers(filePath);

        return ResponseEntity.ok(absolutePath);
    }

    @GetMapping("document/{id}")
    public ResponseEntity<?> getDocById(@PathVariable(value = "id") int id){
        try {
            return ResponseEntity.ok(documentCandidatureService.getById(id));
        }catch (ResponeExtendException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("document inexistant" );
        }

    }
    @GetMapping("by-candidature/{id}")
    public ResponseEntity<?> getDocByCandidatureId(@PathVariable(value = "id") int id){
        try {
            return ResponseEntity.ok(documentCandidatureService.getByCandidature(id));
        }catch (ResponeExtendException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("document inexistant" );
        }

    }


    @GetMapping("/by-candidature")
    public ResponseEntity<?> getDocByCandidatureId(){
        try {
            return ResponseEntity.ok(documentCandidatureService.getAllByCandidature());
        }catch (ResponeExtendException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("document inexistant" );
        }

    }
}
