package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.dto.GestionCandidatureDto;
import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.TestService;
import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.candidature.CandidatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/candidature")
public class CandidatureController {

    @Autowired
    private CandidatureServiceImpl candidatureService;


    /**
     *Methode qui ajoute une candidature
     * @param candidature
     * @return
     */
    @PostMapping("/add")
    public Candidature addCandidature(@RequestBody Candidature candidature){
        return candidatureService.save(candidature);
    }

    /**
     * Methode qui recupere toute les candidatures
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllCandidature() throws WsException{

        try {
           return ResponseEntity.ok(candidatureService.getAll());
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }


    }


    /**
     * Methode qui retourne la candidature par id
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getCandidatureById(@PathVariable int id){
        try {
            candidatureService.getById(id);
           return ResponseEntity.ok(candidatureService.getById(id));
        }catch (WsException e){
            return  ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }

    }


    /**
     *
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    public boolean deleteCandidature(@PathVariable(value = "id") int id){
        if (candidatureService.getById(id) != null){
            candidatureService.delete(id);
            return true;
        }
        return false;
    }


    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateCandidature(@PathVariable(value = "id") int id, @RequestBody Candidature candidature){


            try {
                candidatureService.update(id, candidature);
                return ResponseEntity.ok(candidature);
            }catch (WsException e ) {
                return ResponseEntity.status(e.getStatusCode())
                        .body(e.getMessage());
            }
    }


    @Autowired
    private TestService docCandidatureService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadPdf(@RequestParam("fileCV") MultipartFile fileCV,
                                       @RequestParam("fileLM") MultipartFile fileLM,
                                       @RequestParam(value = "doc", required = false, defaultValue = "--") String valeurSaisieParUtilisateur,
                                       @RequestParam("postId") Integer postId) {
        try {
            DocumentCandidature docCandidature = docCandidatureService.save(fileCV, fileLM, valeurSaisieParUtilisateur, postId);
            return ResponseEntity.ok(new GestionCandidatureDto(docCandidature));
           // return ResponseEntity.ok(docCandidature);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
