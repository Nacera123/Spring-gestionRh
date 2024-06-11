package getionRh.example.rh.controller.candidature;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import getionRh.example.rh.dto.GestionCandidatureDto;
import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.TestService;
import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.candidature.CandidatureServiceImpl;
import getionRh.example.rh.service.implementation.candidature.SessionCandidatureServiceImpl;
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

    @Autowired
    private SessionCandidatureServiceImpl sessionCandidatureService;


    @GetMapping("session/{id}")
    public ResponseEntity<?> candidatureBySession(@PathVariable Integer id){
        try {
            SessionCandidature sessionCandidature = sessionCandidatureService.getById(id);
            List<Candidature> candidatures = candidatureService.candidatureBySession(sessionCandidature);
            System.out.println(candidatures);
            return ResponseEntity.ok(candidatures);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }









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
                                       @RequestParam(value = "fileLM", required = false ) MultipartFile fileLM,
                                       @RequestParam(value = "nomFileCV", required = false, defaultValue = "--") String nomFileCV,
                                       @RequestParam(value = "nomFileLM", required = false, defaultValue = "--") String nomFileLM,
                                       @RequestParam(value = "datas", required = false) String datas,
                                       @RequestParam("postId") Integer postId) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        IndividuDto individuDto = mapper.readValue(datas, IndividuDto.class);

        try {
            return ResponseEntity.ok(docCandidatureService.save(fileCV, fileLM, nomFileCV, nomFileLM,individuDto,  postId));
           // return ResponseEntity.ok(docCandidature);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/upload-user")
    public ResponseEntity<?> uploadPdf1(@RequestParam("fileCV") MultipartFile fileCV,
                                       @RequestParam(value = "fileLM", required = false ) MultipartFile fileLM,
                                       @RequestParam(value = "nomFileCV", required = false, defaultValue = "--") String nomFileCV,
                                       @RequestParam(value = "nomFileLM", required = false, defaultValue = "--") String nomFileLM,
                                       @RequestParam(value = "datas", required = false) String datas,
                                       @RequestParam("postId") Integer postId) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        IndividuDto individuDto = mapper.readValue(datas, IndividuDto.class);



        try {
            DocumentCandidature docCandidature = docCandidatureService.saveUser(fileCV, fileLM, nomFileCV, nomFileLM,individuDto,  postId);
            return ResponseEntity.ok(new GestionCandidatureDto(docCandidature));
           // return ResponseEntity.ok(docCandidature);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
