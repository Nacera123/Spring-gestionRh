package getionRh.example.rh.service;


import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.repository.IndividuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndividuService {

    @Autowired
    private IndividuRepository individuRepository;

    public Individu save(Individu individu){
        return individuRepository.save(individu);
    }
    public List<Individu> getAll(){
        return individuRepository.findAll();
    }

    public Optional<Individu> getById(Integer id){
        return individuRepository.findById(id);
    }

    public void delete(Individu individu){
        individuRepository.delete(individu);
    }


    public Optional<Individu> getByName(String name){
        return individuRepository.findIndividusByNom(name);
    }


}
