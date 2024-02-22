package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.Candidature;

import java.util.List;

public interface CandidatureService {
    Candidature save(Candidature candidature);

    List<Candidature> getAll();

    Candidature getById(Integer id);


    void delete(Integer id);

    Candidature update(Integer id, Candidature candidature);
}
