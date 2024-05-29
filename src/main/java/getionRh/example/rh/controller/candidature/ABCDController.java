package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/gef")
public class ABCDController {

    @Autowired
    private TestService docCandidatureService;

    @GetMapping("/1111")
    public String toto(){
        return "toto";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPdf(@RequestParam("file") MultipartFile file,
                                       @RequestParam("doc") String valeurSaisieParUtilisateur,
                                       @RequestParam("postId") Integer postId) {
       /* try {
            DocumentCandidature docCandidature = docCandidatureService.save(file, valeurSaisieParUtilisateur, postId);
            return ResponseEntity.ok(docCandidature);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }*/
        return null;
    }
}
