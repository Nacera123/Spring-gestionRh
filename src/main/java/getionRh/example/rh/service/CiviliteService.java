package getionRh.example.rh.service;


import getionRh.example.rh.entity.Civilite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CiviliteService {
    List<Civilite> getAll();

    Civilite save(Civilite civilite);

    Civilite getByDesignation(String designation);

    long count();
}
