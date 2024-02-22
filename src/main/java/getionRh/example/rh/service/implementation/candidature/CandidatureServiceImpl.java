package getionRh.example.rh.service.implementation.candidature;


import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.CandidatureRepository;
import getionRh.example.rh.service.candidature.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatureServiceImpl implements CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;


    @Override
    public Candidature save(Candidature candidature){
        return candidatureRepository.save(candidature);
    }


    @Override
    public List<Candidature> getAll()throws WsException{

        List<Candidature> candidatures = candidatureRepository.findAll();
        if (candidatures.isEmpty()){
            throw new WsException(HttpStatus.NOT_FOUND, "La liste de candidature est vide");
        }else {
            return candidatures;
        }

    }

    @Override
    public Candidature getById(Integer id)throws WsException {
        Optional<Candidature> optional = candidatureRepository.findById(id);
        Candidature candidature;
        if (optional.isPresent()){
            candidature = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "⚠ La candidature " + id + " n'existe pas!");
        }
         return candidature;
    }

    @Override
    public void delete(Integer id){

        candidatureRepository.deleteById(id);
    }

    @Override
    public Candidature update(Integer id, Candidature candidature){
        Candidature candidature1 = this.getById(id);
        candidature1.setEtatCandidature(candidature.getEtatCandidature());
        candidature1.setIndividu(candidature.getIndividu());
        candidature1.setPoste(candidature.getPoste());
        return this.save(candidature1);
    }
}
