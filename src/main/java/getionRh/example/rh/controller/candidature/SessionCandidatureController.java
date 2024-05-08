package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.entity.candidature.PosteDeTravail;
import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.SessionCandidatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/session-candidature")
public class SessionCandidatureController {

    @Autowired
    private  SessionCandidatureServiceImpl sessionCandidatureService;


    @GetMapping("/list")
    public ResponseEntity<?> getAllSession(){
        try {
            return ResponseEntity.ok(sessionCandidatureService.getAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());


        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSession(@RequestBody SessionCandidature session) {
        try {
            SessionCandidature savedSession = sessionCandidatureService.save(session);
            return ResponseEntity.ok(savedSession);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPosteById(@PathVariable Integer id) {
        try {
            SessionCandidature session= sessionCandidatureService.getById(id);
            return ResponseEntity.ok(session);
        } catch (WsException e ) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }


    @PostMapping("/{id}/update")
    public ResponseEntity<?> updatePoste(@PathVariable(value = "id") int id, @RequestBody SessionCandidature session)throws Exception{

        try {
            sessionCandidatureService.update(id, session);
            SessionCandidature sessionCandidatureMiseAJour = sessionCandidatureService.getById(id);
            return  ResponseEntity.ok(sessionCandidatureMiseAJour);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public void deletePosteDeTravailService(@PathVariable int id){


        sessionCandidatureService.delete(id);

    }


    @GetMapping("/detail/{reference}")
    public SessionCandidature getByRef(@PathVariable String reference)throws Exception {
        return sessionCandidatureService.getByReference(reference);
    }

}
