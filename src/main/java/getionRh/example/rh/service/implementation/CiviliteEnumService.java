package getionRh.example.rh.service.implementation;

import getionRh.example.rh.enumerate.EtatCivilEnum;
import org.springframework.stereotype.Service;

@Service
public class CiviliteEnumService {


    public EtatCivilEnum getByDesignation(String designation) {
        return EtatCivilEnum.getEtatCivilEnumFromDesignation(designation);
    }
}
