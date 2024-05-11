package getionRh.example.rh.service.implementation;

import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.repository.PaysRepository;
import getionRh.example.rh.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PaysServiceImpl implements PaysService {

    @Autowired  private PaysRepository paysRepository;


    @Override
    public long counPays(){
        return paysRepository.count();
    }

    @Override
    public Pays save(Pays pays){
        return  paysRepository.save(pays);
    }

    @Override
    public List<Pays> getAll() {
        return paysRepository.findAll();
    }

    @Override
    public Optional<Pays> getById(Integer id) {
        return paysRepository.findById(id);
    }

    @Override
    public void delete(Pays pays) {
        paysRepository.delete(pays);
    }


    @Override
    public  Pays getDesignation(String designation){
        return paysRepository.findByDesignationIgnoreCase(designation);
    }
}
