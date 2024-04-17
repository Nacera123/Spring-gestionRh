package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.entity.candidature.PosteDeTravail;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.PosteDeTravailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/add")
    public PosteDeTravail addPoste(@RequestBody PosteDeTravail posteDeTravail){
        return posteDeTravailService.save(posteDeTravail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPosteById(@PathVariable Integer id) {
        try {
            PosteDeTravail posteDeTravail = posteDeTravailService.getById(id);
            return ResponseEntity.ok(posteDeTravail);
        } catch (WsException e ) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    /**
     * Update
     * @param id
     * @param posteDeTravail
     * @return
     */
    @PostMapping("/{id}/update")
    public PosteDeTravail updatePoste(@PathVariable(value = "id") int id, @RequestBody PosteDeTravail posteDeTravail){
//        try {
//            posteDeTravailService.update(id, posteDeTravail);
//
//            PosteDeTravail posteDeTravailMiseAJour = posteDeTravailService.getById(id);
//            return  ResponseEntity.ok(posteDeTravailMiseAJour);
//        }catch (WsException e){
//            return ResponseEntity.status(e.getStatusCode())
//                    .body(e.getMessage());
//        }
        return      posteDeTravailService.update(id, posteDeTravail);

    }



    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deletePosteDeTravailService(@PathVariable int id){
//
//        try {
//            PosteDeTravail p = posteDeTravailService.getById(id);
//            if (p != null){
//                posteDeTravailService.delete(id);
//                return ResponseEntity.ok("le poste " + p.getNom() + " a bien été supprimé");
//            }else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("ce poste n'existe pas 😂😂😂");
//            }
//        }catch (WsException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("ce poste n'existe pas 😂😂😂");
//            // .body("Une erreur s'est produite lors de la suppression du poste");
//        }
//    }
    public void deletePosteDeTravailService(@PathVariable int id){

  
                posteDeTravailService.delete(id);

    }



}
