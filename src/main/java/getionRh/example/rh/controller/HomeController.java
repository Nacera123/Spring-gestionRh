package getionRh.example.rh.controller;

import getionRh.example.rh.service.IndividuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IndividuService individuService;

    @GetMapping
    public String home(){
        return
                "toto";
    }

//    @Autowired
//    private EtatCivilService etatCivilService;

//    @GetMapping
//    public String getMethodName() {
//        return "home";
//    }
//
//    @GetMapping("/individu")
//    public List<IndividuDto> getIndividu(){
//        List<Individu> individus = individuService.getAll();
//        List<IndividuDto> individuDtos = new ArrayList<>();
//
//        for (Individu i : individus){
//                individuDtos.add(new IndividuDto(i));
//        }
//        return individuDtos;
//    }
//    public List<Individu> getIndividu(){
//        List<Individu> individus = individuService.getAll();
//        List<IndividuDto> individuDtos = new ArrayList<>();
//
//        for (Individu i : individus){
//                individuDtos.add(new IndividuDto(i));
//        }
//        return individus;
//    }

//    @GetMapping("/etat-civil")
//    public List<EtatCivilDto> getEtatCivil(){
//        List<EtatCivil> etatCivils = etatCivilService.getAll();
//        List<EtatCivilDto> etatCivilDtos = new ArrayList<>();
//        for (EtatCivil e : etatCivils){
//            etatCivilDtos.add(new EtatCivilDto(e));
//        }
//        return etatCivilDtos;
//
//    }



}
