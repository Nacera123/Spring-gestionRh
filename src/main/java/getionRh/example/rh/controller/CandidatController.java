package getionRh.example.rh.controller;


import getionRh.example.rh.dto.CandidatDto;
import getionRh.example.rh.entity.Candidat;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.enumerate.EtatCivilEnum;
import getionRh.example.rh.enumerate.SituationFamilialeEnum;
import getionRh.example.rh.service.implementation.CandidatServiceImpl;
import getionRh.example.rh.service.implementation.IndividuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candidat")
public class CandidatController {

    @Autowired private CandidatServiceImpl candidatService;
    @Autowired private IndividuServiceImpl individuService;


    @GetMapping
    public List<CandidatDto> getAllCandidat(){
        List<Candidat> candidatList = candidatService.getAll();
        List<CandidatDto> candidatDtoList = new ArrayList<>();
        for (Candidat candidat : candidatList){
            candidatDtoList.add(new CandidatDto(candidat));
        }
        return candidatDtoList;
    }

    @PostMapping("/add")
    public CandidatDto addCandidat(@RequestBody Individu individu,@RequestParam  String etaCivil, @RequestParam String situationFamiliale){
        EtatCivilEnum etatCivilEnum = EtatCivilEnum.getEtatCivilEnumFromDesignation(etaCivil);
        individu.setEtatCivilEnum(etatCivilEnum);

        SituationFamilialeEnum situationFamilialeEnum = SituationFamilialeEnum.getSituationFamilialeEnumByDesignation(situationFamiliale);
        individu.setSituationFamiliale(situationFamilialeEnum);
        individuService.save(individu);
        Candidat candidat= new Candidat();
        candidat.setIndividu(individu);
        return new CandidatDto(candidatService.save(candidat));
    }

}
