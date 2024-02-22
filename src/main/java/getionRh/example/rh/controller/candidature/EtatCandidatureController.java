package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.entity.candidature.EtatCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.EtatCandidatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/etat-candidature")
public class EtatCandidatureController {

    @Autowired
    private EtatCandidatureServiceImpl etatCandidatureService;

    @PostMapping("/add")
    public EtatCandidature saveEtatCandidature(EtatCandidature etatCandidature){
        return etatCandidatureService.save(etatCandidature);
    }


    @GetMapping("/list")
    public ResponseEntity<?> getAllEtatCandidature(){

        try {
            return ResponseEntity.ok(etatCandidatureService.getAll());
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<?> getEtatCategorieById(@PathVariable(value = "id") int id){
        try{
            return ResponseEntity.ok(etatCandidatureService.getById(id));
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }


    @PutMapping("/delete/{id}")
    public boolean deleteEtatCategorie(@PathVariable(value = "id") int id){
        if (etatCandidatureService.getById(id) !=null){
            etatCandidatureService.delete(id);
            return true;
        }
        return false;
    }


    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateEtatCategorie(@PathVariable(value = "id") int id, @RequestBody EtatCandidature etatCandidature){

        try {
            return ResponseEntity.ok(etatCandidatureService.update(id, etatCandidature));
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }


}
