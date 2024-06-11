package getionRh.example.rh.service.implementation;

import getionRh.example.rh.dto.IndividuDto;
import getionRh.example.rh.entity.Candidat;
import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.repository.CandidatRepository;
import getionRh.example.rh.repository.IndividuRepository;
import getionRh.example.rh.service.CandidatService;
import getionRh.example.rh.service.IndividuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatServiceImpl implements CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private IndividuRepository individuRepository;


    @Autowired private IndividuService individuService;
    @Override
    public Candidat save(Candidat candidat) {
//        Individu individu = candidat.getIndividu();
//        Individu individuSaved = individuService.save(individu);
//        candidat.setIndividu(individuSaved);
       return candidatRepository.save(candidat);
    }

    @Override
    public List<Candidat> getAll() {
        return candidatRepository.findAll();
    }

    @Override
    public Optional<Candidat> getById(Integer id) {
        return candidatRepository.findById(id);
    }

    @Override
    public void delete(Candidat candidat) {
        candidatRepository.delete(candidat);
    }


    public Candidat save1(Candidat candidat){
       return candidatRepository.save(candidat);
    }
}
