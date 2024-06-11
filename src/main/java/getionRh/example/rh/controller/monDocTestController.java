package getionRh.example.rh.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import getionRh.example.rh.dto.GestionCandidatureDto;
import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.Candidat;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.candidature.CandidatureServiceImpl;
import getionRh.example.rh.service.monDocTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mon-doc")
public class monDocTestController {

    @Autowired
    private monDocTest monDocTest;

    @Autowired
    private CandidatServiceImpl candidatRepository;




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

            DocumentCandidature docCandidature = monDocTest.saveCandidature(fileCV, fileLM, nomFileCV, nomFileLM,individuDto,  postId);
            return ResponseEntity.ok(new GestionCandidatureDto(docCandidature));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}