package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.PosteDeTravailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/poste-de-travail")
public class PosteDeTravailController {

    @Autowired
    private PosteDeTravailServiceImp posteDeTravailService;



    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(posteDeTravailService.getAll());
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }
}
