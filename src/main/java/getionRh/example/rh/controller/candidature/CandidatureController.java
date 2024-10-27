package getionRh.example.rh.controller.candidature;

import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.CandidatureServiceImpl;
import getionRh.example.rh.service.implementation.candidature.SessionCandidatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/candidature")
@CrossOrigin(origins = "*")
public class CandidatureController {

    @Autowired
    private CandidatureServiceImpl candidatureService;

    @Autowired
    private SessionCandidatureServiceImpl sessionCandidatureService;

    @GetMapping("session/{id}")
    public ResponseEntity<?> candidatureBySession(@PathVariable Integer id) {
        try {
            SessionCandidature sessionCandidature = sessionCandidatureService.getById(id);
            List<Candidature> candidatures = candidatureService.candidatureBySession(sessionCandidature);
            System.out.println(candidatures);
            return ResponseEntity.ok(candidatures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Methode qui ajoute une candidature
     * 
     * @param candidature
     * @return
     */
    @PostMapping("/add")
    public Candidature addCandidature(@RequestBody Candidature candidature) {
        return candidatureService.save(candidature);
    }


    @GetMapping("/list")
    public ResponseEntity<?> getAllCandidature() {

        try {
            return ResponseEntity.ok(candidatureService.getAll());
        } catch (WsException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }

    }

    /**
     * Methode qui retourne la candidature par id
     * 
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getCandidatureById(@PathVariable int id) {
        try {
            candidatureService.getById(id);
            return ResponseEntity.ok(candidatureService.getById(id));
        } catch (WsException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    public boolean deleteCandidature(@PathVariable(value = "id") int id) {
        if (candidatureService.getById(id) != null) {
            candidatureService.delete(id);
            return true;
        }
        return false;
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateCandidature(@PathVariable(value = "id") int id,
            @RequestBody Candidature candidature) {

        try {
            candidatureService.update(id, candidature);
            return ResponseEntity.ok(candidature);
        } catch (WsException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    @GetMapping("/aaaa/{id}")
    public ResponseEntity<?> getMethodName(@RequestParam(value = "id") int id) {
        try {
            return ResponseEntity.ok(candidatureService.candidatureParCandidats(id));

        } catch (Exception a) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(a.getCause());
        }

    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<?> toto(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(candidatureService.test(id));
    }

    @GetMapping("/poste-vacant/{nom}")
    public ResponseEntity<?> getCandidatureByPosteVacant(@PathVariable(value = "nom") String nom){
        return ResponseEntity.ok(candidatureService.getByposteVacant(nom));
    }

}
