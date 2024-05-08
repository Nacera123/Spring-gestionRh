package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.entity.candidature.NomDocument;
import getionRh.example.rh.exception.ResponeExtendException;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.NomDocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/nom-document")
public class NomDocumentController {

    @Autowired
    private NomDocumentServiceImpl nomDocumentService;


    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(nomDocumentService.getAll());
        }catch (ResponeExtendException e){
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody NomDocument nomDocument) {
        try {
            return ResponseEntity.ok(nomDocumentService.save(nomDocument));
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNomDocById(@RequestParam int id) {
        try {
            return ResponseEntity.ok(nomDocumentService.getById(id));
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<?> updateNomDoc(@PathVariable int id, @RequestBody NomDocument nomDocument) {
        try {
            return ResponseEntity.ok(nomDocumentService.update(id, nomDocument));

        }catch (WsException e){
          return   ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public void delete( @RequestBody NomDocument nomDocument , @RequestParam int id) {
            NomDocument docId = nomDocumentService.getById(id);

            if (docId != null) {
            nomDocumentService.delete(docId);

            }
    }
}
