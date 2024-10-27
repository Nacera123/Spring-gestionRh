package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.entity.candidature.PosteDeTravail;
import getionRh.example.rh.entity.candidature.PosteVacant;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.PosteVacantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poste-vacant")
@CrossOrigin(origins = "*")
public class PosteVancantController {

    @Autowired
    private PosteVacantServiceImpl posteVacantService;




    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(posteVacantService.getAll());

        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }

    }



    @PostMapping("/add")
    public ResponseEntity<?> addPosteVacant(@RequestBody PosteVacant posteVacant) throws WsException{
        try {
            PosteVacant poste = posteVacantService.save(posteVacant);
            return ResponseEntity.ok(poste);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPosteVacantById(@PathVariable int id) throws WsException{
        try {
            return ResponseEntity.ok(posteVacantService.getById(id));
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }



    @PostMapping("/{id}/update")
    public ResponseEntity<?> updatePoste(@PathVariable(value = "id") int id, @RequestBody PosteVacant posteDeTravail)throws Exception{

        try {
            posteVacantService.update(id, posteDeTravail);
            PosteVacant posteDeTravailMiseAJour = posteVacantService.getById(id);
            return  ResponseEntity.ok(posteDeTravailMiseAJour);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( e.getMessage());
        }


    }


    @DeleteMapping("/delete/{id}")
    public void deletePosteDeTravailService(@PathVariable int id){


        posteVacantService.delete(id);

    }

}
