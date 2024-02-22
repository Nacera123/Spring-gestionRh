package getionRh.example.rh.service.implementation.candidature;


import getionRh.example.rh.entity.candidature.TypeDeContrat;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.TypeDeContratRepository;
import getionRh.example.rh.service.candidature.TypeDeContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeDeContratServiceImpl implements TypeDeContratService {

    @Autowired
    private TypeDeContratRepository typeDeContratRepository;


    @Override
    public TypeDeContrat save(TypeDeContrat contrat){
        return typeDeContratRepository.save(contrat);
    }


    @Override
    public List<TypeDeContrat> getAll(){
        return typeDeContratRepository.findAll();
    }


    @Override
    public TypeDeContrat getById(Integer id)throws WsException {
        Optional<TypeDeContrat> optional = typeDeContratRepository.findById(id);
        TypeDeContrat contrat;
        if (optional.isPresent()){
            contrat = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return contrat;
    }

    @Override
    public void delete(TypeDeContrat contrat){
        typeDeContratRepository.delete(contrat);
    }

    @Override
    public TypeDeContrat update(Integer id, TypeDeContrat contrat){
        TypeDeContrat contrat1 = this.getById(id);
        contrat1.setType(contrat.getType());
        return this.save(contrat1);
    }






    @Override
    public long count(){
        return typeDeContratRepository.count();
    }
}
