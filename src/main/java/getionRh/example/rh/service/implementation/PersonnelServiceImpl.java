package getionRh.example.rh.service.implementation;

import getionRh.example.rh.entity.Personnel;
import getionRh.example.rh.repository.PersonnelRepository;
import getionRh.example.rh.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;
    @Override
    public Personnel save(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public List<Personnel> getAll() {
        return personnelRepository.findAll();
    }

    @Override
    public Optional<Personnel> getById(Integer id) {
        return personnelRepository.findById(id);
    }

    @Override
    public void delete(Personnel personnel) {
        personnelRepository.delete(personnel);
    }
}
