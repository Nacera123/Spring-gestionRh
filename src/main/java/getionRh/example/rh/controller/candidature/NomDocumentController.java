package getionRh.example.rh.controller.candidature;


import getionRh.example.rh.entity.candidature.NomDocument;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.candidature.NomDocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());

        }
    }


    @PostMapping("/add")
    public ResponseEntity<?>addPoste(@RequestBody NomDocument posteDeTravail)throws Exception{
        try {
            NomDocument posteDeTravail1 = nomDocumentService.save(posteDeTravail);
            return ResponseEntity.ok(posteDeTravail1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( e.getMessage());
        }

        //return posteDeTravailService.save(posteDeTravail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPosteById(@PathVariable Integer id) {
        try {
            NomDocument posteDeTravail = nomDocumentService.getById(id);
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
    public ResponseEntity<?> updatePoste(@PathVariable(value = "id") int id, @RequestBody NomDocument posteDeTravail)throws Exception{

        try {
            nomDocumentService.update(id, posteDeTravail);
            NomDocument posteDeTravailMiseAJour = nomDocumentService.getById(id);
            return  ResponseEntity.ok(posteDeTravailMiseAJour);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( e.getMessage());
        }


    }



    @DeleteMapping("/delete/{id}")
    public void deletePosteDeTravailService(@PathVariable int id){


        nomDocumentService.delete(id);

    }


    @GetMapping("/detail/{nom}")
    public NomDocument getPosteByNom(@PathVariable String nom)throws Exception{
        return nomDocumentService.getByNom(nom);
    }



}
