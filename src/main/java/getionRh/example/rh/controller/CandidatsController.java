package getionRh.example.rh.controller;


import getionRh.example.rh.entity.Candidats;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.service.implementation.IndividuServiceImpl;
import getionRh.example.rh.service.implementation.CandidatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidats")
public class CandidatsController {

    @Autowired
    private CandidatsService candidatsService;
    @Autowired
    private IndividuServiceImpl individuService;

    @PostMapping("/add")
    public Candidats add(@RequestParam int id){
        Individu individu = individuService.getById(id).orElse(null);
        Candidats candidats = new Candidats();
        candidats.setIndividu(individu);
        return candidatsService.save(candidats);
    }

}
