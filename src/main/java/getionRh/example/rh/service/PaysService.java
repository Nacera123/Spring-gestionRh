package getionRh.example.rh.service;


import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;


    public long counPays(){
        return paysRepository.count();
    }


    public Pays save(Pays pays){
        return  paysRepository.save(pays);
    }
}
