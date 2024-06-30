package getionRh.example.rh.controller;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.candidature.*;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/test")
public class TestController {

    @Value("${file.upload.path}")
    private String pathUploadFile;

    @Autowired
    private DocumentCandidatureServiceImpl documentCandidatureService;

    @Autowired
    private CandidatServiceImpl candidatRepository;

    @GetMapping("/toto/{filePath}")
    public String toto(@PathVariable String filePath) {

        String absolutePath = documentCandidatureService.getCheminCompletFichiers(filePath);
        return absolutePath;
    }

    @GetMapping("/toto/fifi/{filePath}")
    public ResponseEntity<?> fifi(@PathVariable String filePath) {

        String absolutePath = documentCandidatureService.getCheminCompletFichiers(filePath);

        return ResponseEntity.ok(absolutePath);
    }

    @GetMapping("/toto/titi/{filePath}")
    public ResponseEntity<Resource> titi(@PathVariable String filePath) {
        String absolutePath = documentCandidatureService.getCheminCompletFichiers(filePath);

        try {
            Path path = Paths.get(absolutePath);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/toto/mimi/{filePath}")
    public void mimi(@PathVariable String filePath, HttpServletResponse response) {

        try {
            Path path = Paths.get(documentCandidatureService.getCheminCompletFichiers(filePath));
            if (Files.exists(path)) {
                response.setContentType(pathUploadFile);
                response.addHeader("Content-Disposition", "attachement; filename" + path.getFileName());
                Files.copy(path, response.getOutputStream());
                response.getOutputStream().flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
