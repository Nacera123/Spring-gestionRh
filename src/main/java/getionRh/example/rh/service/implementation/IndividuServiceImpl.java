package getionRh.example.rh.service.implementation;

import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.IndividuRepository;
import getionRh.example.rh.service.IndividuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IndividuServiceImpl implements IndividuService {

    @Autowired private IndividuRepository individuRepository;


    @Override
    public Individu save(Individu individu)throws WsException {
        String regexp = "^|([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)";

        if (individu.getPrenom().isEmpty() || individu.getNom().isEmpty() || individu.getTelephone().isEmpty()){
            throw new WsException(HttpStatus.BAD_REQUEST, "Veuillez remplir tous les champs");
        }
        if (!individu.getEmail().matches(".+@.+\\.[a-z]+")){
            throw  new WsException(HttpStatus.BAD_REQUEST, "Entrez un email valide " + individu.getEmail());
        }

        return individuRepository.save(individu);
    }
    @Override
    public List<Individu> getAll(){
        return individuRepository.findAll();
    }


    @Override
    public Optional<Individu> getById(Integer id){
        return individuRepository.findById(id);
    }


    @Override
    public void delete(Individu individu){
        individuRepository.delete(individu);
    }


    @Override
    public Optional<Individu> getByName(String name){
        return individuRepository.findIndividusByNom(name);
    }

    public Individu getByEmail (String email){
        return individuRepository.findByEmailIgnoreCase(email);
    }
}
