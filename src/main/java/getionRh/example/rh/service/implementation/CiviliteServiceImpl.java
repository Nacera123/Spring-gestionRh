package getionRh.example.rh.service.implementation;


import getionRh.example.rh.entity.Civilite;
import getionRh.example.rh.repository.CiviliteRepository;
import getionRh.example.rh.service.CiviliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiviliteServiceImpl implements CiviliteService {

    @Autowired
    private CiviliteRepository civiliteRepository;

    @Override
    public List<Civilite> getAll(){
        return civiliteRepository.findAll();
    }

    @Override
    public Civilite save(Civilite civilite){
        return civiliteRepository.save(civilite);
    }


    @Override
    public Civilite getByDesignation(String designation){
        return civiliteRepository.findByDesignationIgnoreCase(designation);
    }
    @Override
    public long count(){
        return civiliteRepository.count();
    }
}
