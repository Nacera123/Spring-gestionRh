package getionRh.example.rh.service;


import getionRh.example.rh.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;
}
