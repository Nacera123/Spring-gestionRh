package getionRh.example.rh.service;


import getionRh.example.rh.entity.Candidat;
import getionRh.example.rh.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CandidatService {

    public Candidat save(Candidat candidat);
    public List<Candidat> getAll();
    public Optional<Candidat> getById(Integer id);
    public void delete(Candidat candidat);

}
