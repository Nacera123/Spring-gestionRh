package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.EtatCandidature;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface EtatCandidatureService {
    EtatCandidature save(EtatCandidature etat);

    List<EtatCandidature> getAll();

    EtatCandidature getById(Integer id)throws WsException;

    void delete(EtatCandidature etat);

    EtatCandidature update(Integer id, EtatCandidature etat);

    long count();
}
