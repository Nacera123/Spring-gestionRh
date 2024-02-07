package getionRh.example.rh.service;


import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PaysService {

        public long counPays();
        public Pays save(Pays pays);
        public List<Pays> getAll();
        public Optional<Pays> getById(Integer id);
        public void delete(Pays pays);


}
