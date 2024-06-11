package getionRh.example.rh.service.implementation;


import getionRh.example.rh.entity.Candidats;
import getionRh.example.rh.repository.CandidatsReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatsService {

    @Autowired
    private CandidatsReository candidatsReository;


    public Candidats save(Candidats toto){
        return candidatsReository.save(toto);
    }
}
