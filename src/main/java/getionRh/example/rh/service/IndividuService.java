package getionRh.example.rh.service;


import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.repository.IndividuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IndividuService {

    public Individu save(Individu individu);
    public List<Individu> getAll();
    public Optional<Individu> getById(Integer id);
        public void delete(Individu individu);
    public Optional<Individu> getByName(String name);

}
