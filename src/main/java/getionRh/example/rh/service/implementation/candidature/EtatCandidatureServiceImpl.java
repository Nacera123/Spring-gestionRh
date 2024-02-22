package getionRh.example.rh.service.implementation.candidature;


import getionRh.example.rh.entity.candidature.EtatCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.EtatCandidatureRepository;
import getionRh.example.rh.service.candidature.EtatCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtatCandidatureServiceImpl implements EtatCandidatureService {


    @Autowired
    private EtatCandidatureRepository etatCandidatureRepository;


    @Override
    public EtatCandidature save(EtatCandidature etat){
        return etatCandidatureRepository.save(etat);
    }

    @Override
    public List<EtatCandidature> getAll(){
        return etatCandidatureRepository.findAll();
    }

    @Override
    public EtatCandidature getById(Integer id)throws WsException {
        Optional<EtatCandidature> optional = etatCandidatureRepository.findById(id);
        EtatCandidature etat;
        if (optional.isPresent()){
            etat = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return etat;
    }

    @Override
    public void delete(EtatCandidature etat){
        etatCandidatureRepository.delete(etat);
    }


    @Override
    public EtatCandidature update(Integer id, EtatCandidature etat){
        EtatCandidature etat1 = this.getById(id);
        etat1.setId(etat.getId());
        return etatCandidatureRepository.save(etat1);
    }

    @Override
    public long count(){
        return etatCandidatureRepository.count();
    }
}