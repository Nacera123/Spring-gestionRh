package getionRh.example.rh.controller.candidature;

import getionRh.example.rh.entity.candidature.PosteDeTravail;
import getionRh.example.rh.entity.candidature.TypeDeContrat;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.PosteDeTravailServiceImp;
import getionRh.example.rh.service.implementation.candidature.TypeDeContratServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/type-contrat")
public class TypeDeContratController {


    @Autowired
    private TypeDeContratServiceImpl typeDeContratService;
    @Autowired
    private PosteDeTravailServiceImp posteDeTravailServiceImp;


    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(typeDeContratService.getAll());
        }catch (WsException e ){
            return  ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{type}")
    public TypeDeContrat getByType(@PathVariable String type) throws Exception{
        return typeDeContratService.getByType(type);
    }
}
