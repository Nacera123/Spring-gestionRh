package getionRh.example.rh.service;


import getionRh.example.rh.entity.Personnel;
import getionRh.example.rh.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonnelService {

    public Personnel save(Personnel personnel);
    public List<Personnel> getAll();
    public Optional<Personnel> getById(Integer id);
    public void delete(Personnel personnel);

}
