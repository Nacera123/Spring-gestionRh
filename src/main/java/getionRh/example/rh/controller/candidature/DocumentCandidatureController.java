package getionRh.example.rh.controller.candidature;



import getionRh.example.rh.dto.GestionCandidatureDto;
import getionRh.example.rh.dto.TestDto;
import getionRh.example.rh.entity.Candidat;
import getionRh.example.rh.entity.Civilite;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.entity.candidature.*;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.CiviliteServiceImpl;
import getionRh.example.rh.service.implementation.IndividuServiceImpl;
import getionRh.example.rh.service.implementation.PaysServiceImpl;
import getionRh.example.rh.service.implementation.candidature.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private CandidatServiceImpl candidatService;


    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(documentCandidatureService.getAll());
        }catch (WsException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getMessage());
        }
    }

    @PostMapping("/ajout1")
    public ResponseEntity<?> ajouter1(){
        return null;
    }

    @PostMapping("/ajout")
    public ResponseEntity<?> ajouter(@RequestBody DocumentCandidature d,
            @RequestParam("file") MultipartFile file, @RequestParam Integer posteId) throws Exception {


        String lePays = d.getCandidature().getIndividu().getPays().getDesignation();
        // 2 -A -1 pays  de Type Pays de Individu
        Pays nomPays = paysService.getDesignation(lePays);

        String civ = d.getCandidature().getIndividu().getCivilite().getDesignation();
        Civilite civilite = civiliteService.getByDesignation(civ);


        PosteVacant posteVacant = posteVacantService.getById(posteId);



        Candidature candidature = new Candidature();
        Individu individu = new Individu();
        individu.setNom(d.getCandidature().getIndividu().getNom());
        individu.setPrenom(d.getCandidature().getIndividu().getPrenom());
        individu.setTelephone(d.getCandidature().getIndividu().getTelephone());
        individu.setEmail(d.getCandidature().getIndividu().getEmail());
        individu.setPays(nomPays);
        individu.setCivilite(civilite);

        individuService.save(individu);


        Candidat candidat = new Candidat();
        candidat.setIndividu(individu);
        candidatService.save(candidat);

        candidature.setIndividu(individu);
        //2-B etatCandidature de candidature de Type EtatCandidature
        EtatCandidature etatCandidature = etatCandidatureService.getByEtat("Candidature envoyée");
        candidature.setEtatCandidature(etatCandidature);
        candidature.setPosteVacant(posteVacant);



        candidatureService.save(candidature);
        d.setCandidature(candidature);
        d.getCandidature().setIndividu(individu);

        documentCandidatureService.save2(file, posteId);
        return ResponseEntity.ok(new GestionCandidatureDto(d));





//
//        Candidature candidature = new Candidature();
//        Individu individu = new Individu();
//        individu.setNom(nom);
//        Pays nomPays = paysService.getDesignation(pays);
//        individu.setPays(nomPays);
//        Civilite civilite1 = civiliteService.getByDesignation(civilite);
//        individu.setCivilite(civilite1);
//        individuService.save(individu);
//
//        candidature.setIndividu(individu);
//        candidatureService.save(candidature);
//
//        EtatCandidature etatCandidature = etatCandidatureService.getByEtat("Candidature envoyée");
//        candidature.setEtatCandidature(etatCandidature);
//
//        DocumentCandidature docCandidature = new DocumentCandidature();
//        docCandidature.setNomPieceJointe(nomDocument);
//        docCandidature.setCandidature(candidature);
//
//        docCandidature = documentCandidatureService.save2(file, postId);
//
//        return ResponseEntity.ok(new GestionCandidatureDto(docCandidature));




        // Créer et remplir les entités nécessaires (Individu, Candidature, etc.)
//        NomDocument nomDocument = nomDocumentService.getByNom(nom);
//        if (nomDocument == null) {
//            throw new Exception("Pas de nom de document");
//        }

    }



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
