package getionRh.example.rh.controller.candidature;



import getionRh.example.rh.dto.GestionCandidatureDto;
import getionRh.example.rh.entity.Civilite;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import getionRh.example.rh.entity.candidature.EtatCandidature;
import getionRh.example.rh.entity.candidature.NomDocument;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.CiviliteServiceImpl;
import getionRh.example.rh.service.implementation.IndividuServiceImpl;
import getionRh.example.rh.service.implementation.PaysServiceImpl;
import getionRh.example.rh.service.implementation.candidature.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/document-candidature")
public class DocumentCandidatureController {

    @Autowired
    private DocumentCandidatureServiceImpl documentCandidatureService;

    @Autowired
    private IndividuServiceImpl individuService;

    @Autowired
    private NomDocumentServiceImpl nomDocumentService;


    @Autowired
    private CandidatureServiceImpl candidatureService;

    @Autowired
    private PaysServiceImpl paysService;


    @Autowired
    private PosteVacantServiceImpl posteVacantService;

    @Autowired
    private EtatCandidatureServiceImpl etatCandidatureService;

    @Autowired
    private CiviliteServiceImpl civiliteService;


    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(documentCandidatureService.getAll());
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

//    @GetMapping("/enum/{designation}")
//    public EtatCivilEnum getByDes(@PathVariable String designation){
//        return civiliteEnumService.getByDesignation(designation);
//    }
//
//    @GetMapping("/string/string/{designation}")
//    public String getDes(@PathVariable String designation) {
//        EtatCivilEnum etatCivilEnum = EtatCivilEnum.fromString(designation);
//        return etatCivilEnum.getDesignation();
//    }
//
//    @GetMapping("/enum")
//    public List<EtatCivilEnum> getAllEnum(){
//        return EtatCivilEnum.getAll();
//    }

    @PostMapping("/add")
    public GestionCandidatureDto add(@RequestBody DocumentCandidature d,
                                     @RequestParam String civilite,
                                     @RequestParam String pays,
                                     @RequestParam String nom) throws Exception{


        // 1- nomPieceJointe de type NomDocument
           NomDocument nomDocument = nomDocumentService.getByNom(nom);
        if (nomDocument == null) {
            throw new Exception("pas de nom de document");
        }
           d.setNomPieceJointe(nomDocument);

       // 2- candidature de type Candidature
            Candidature candidature = new Candidature();

            //2-A Individu de candidature de Type Individu
                Individu individu = new Individu();

                individu.setNom(d.getCandidature().getIndividu().getNom());
                individu.setPrenom(d.getCandidature().getIndividu().getPrenom());
                individu.setTelephone(d.getCandidature().getIndividu().getTelephone());
                individu.setEmail(d.getCandidature().getIndividu().getEmail());

                // 2 -A -1 pays  de Type Pays de Individu
                    Pays nomPays = paysService.getDesignation(pays);
                individu.setPays(nomPays);
        individuService.save(individu);

                // 2 -A -2 etat civil de EtatCivilEnum dans Individu
                    Civilite civilite1 = civiliteService.getByDesignation(civilite);
             individu.setCivilite(civilite1);

            candidature.setIndividu(individu);
            candidatureService.save(candidature);

            //2-B etatCandidature de candidature de Type EtatCandidature
                EtatCandidature etatCandidature = etatCandidatureService.getByEtat("Candidature consultée par le recruteur");
            candidature.setEtatCandidature(etatCandidature);


            d.setCandidature(candidature);



        d.getCandidature().setIndividu(individu);

        return  new GestionCandidatureDto(documentCandidatureService.save(d));




    }
}
