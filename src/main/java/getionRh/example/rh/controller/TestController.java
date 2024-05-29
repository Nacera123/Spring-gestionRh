package getionRh.example.rh.controller;


import getionRh.example.rh.dto.GestionCandidatureDto;
import getionRh.example.rh.dto.TestDto;
import getionRh.example.rh.entity.Candidat;
import getionRh.example.rh.entity.Civilite;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.entity.candidature.*;
import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.CiviliteServiceImpl;
import getionRh.example.rh.service.implementation.IndividuServiceImpl;
import getionRh.example.rh.service.implementation.PaysServiceImpl;
import getionRh.example.rh.service.implementation.candidature.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/testtest")
public class TestController {

    @Autowired
    private NomDocumentServiceImpl nomDocumentService;
    @Autowired
    private DocumentCandidatureServiceImpl documentCandidatureService;
    @Autowired
    private IndividuServiceImpl individuService;
    @Autowired
    private CandidatureServiceImpl candidatureService;
    @Autowired
    private PaysServiceImpl paysService;
    @Autowired
    private CiviliteServiceImpl civiliteService;
    @Autowired
    private EtatCandidatureServiceImpl etatCandidatureService;

    @Autowired
    private PosteVacantServiceImpl posteVacantService;

    @Autowired
    private CandidatServiceImpl candidatService;

    @PostMapping("/add")
    public TestDto add(@RequestBody DocumentCandidature d,@RequestParam String nom)throws Exception{
        // 1- nomPieceJointe de type NomDocument
        NomDocument nomDocument = nomDocumentService.getByNom(nom);

        if (nomDocument == null) {
            throw new Exception("pas de nom de document");
        }
        d.setNomPieceJointe(nomDocument);

        return  new TestDto(documentCandidatureService.save(d));
    }



    @PostMapping("/add1")
    public TestDto add1(@RequestBody DocumentCandidature d, @RequestParam("posteId") Integer posteId)throws Exception{


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
        return  new TestDto(documentCandidatureService.save(d));
    }














    @PostMapping("/add2")
    public TestDto add2(@RequestBody DocumentCandidature d, @RequestParam("posteId") Integer posteId)throws Exception{


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
        return  new TestDto(documentCandidatureService.save(d));
    }

}
