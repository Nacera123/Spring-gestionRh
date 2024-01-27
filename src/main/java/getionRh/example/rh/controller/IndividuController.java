package getionRh.example.rh.controller;


import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.enumerate.EtatCivilEnum;
import getionRh.example.rh.enumerate.SituationFamilialeEnum;
import getionRh.example.rh.service.IndividuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/individu")
public class IndividuController {

    @Autowired
    private IndividuService individuService;

    /**
     * @param individu
     * @return
     */
    @PostMapping("add")
    public IndividuDto addIndividu(@RequestBody Individu individu, @RequestParam  String etaCivil, @RequestParam String situationFamiliale){
        EtatCivilEnum etatCivilEnum = EtatCivilEnum.getEtatCivilEnumFromDesignation(etaCivil);
        individu.setEtatCivilEnum(etatCivilEnum);

        SituationFamilialeEnum situationFamilialeEnum = SituationFamilialeEnum.getSituationFamilialeEnumByDesignation(situationFamiliale);
        individu.setSituationFamiliale(situationFamilialeEnum);

        return new IndividuDto(individuService.save(individu));
    }



    @GetMapping
    public List<IndividuDto> getAllIndividu(){
        List<Individu> individuList= individuService.getAll();
        List<IndividuDto> individuDtoList = new ArrayList<>();
            for (Individu i : individuList){
                individuDtoList.add(new IndividuDto(i));
            }
            return individuDtoList;

    }


    @GetMapping("/{id}")
    public IndividuDto getIndividuById(@PathVariable Integer id){
        return new IndividuDto(individuService.getById(id).orElse(null));
    }

    @PostMapping("/update/{id}")
    public IndividuDto updateIndividu(@PathVariable int id, @RequestBody Individu individu){
        if (individuService.getById(id) != null){
            individu.setId(id);
            return new IndividuDto(individuService.save(individu));
        }else{
            return new IndividuDto(individu);
        }
    }


     @PutMapping("/delete/{id}")
    public void deleteIndividu(@PathVariable int id, Individu individu){
            if(individuService.getById(id).isPresent()){
                individuService.delete(individu);
            }
    }


    @GetMapping("/nom/{name}")
    public IndividuDto getIndividuByNom(@PathVariable String nom){
        return new IndividuDto(individuService.getByName(nom).orElse(null));
    }
}
