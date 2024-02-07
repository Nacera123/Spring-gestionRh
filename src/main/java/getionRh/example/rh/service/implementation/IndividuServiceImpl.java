package getionRh.example.rh.service.implementation;

import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.repository.IndividuRepository;
import getionRh.example.rh.service.IndividuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IndividuServiceImpl implements IndividuService {

    @Autowired private IndividuRepository individuRepository;


    @Override
    public Individu save(Individu individu){
        return individuRepository.save(individu);
    }
    @Override
    public List<Individu> getAll(){
        return individuRepository.findAll();
    }


    @Override
    public Optional<Individu> getById(Integer id){
        return individuRepository.findById(id);
    }


    @Override
    public void delete(Individu individu){
        individuRepository.delete(individu);
    }


    @Override
    public Optional<Individu> getByName(String name){
        return individuRepository.findIndividusByNom(name);
    }
}
