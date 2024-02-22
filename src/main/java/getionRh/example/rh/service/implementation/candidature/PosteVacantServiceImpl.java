package getionRh.example.rh.service.implementation.candidature;



import getionRh.example.rh.entity.candidature.PosteVacant;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.PosteVacantRepository;
import getionRh.example.rh.service.candidature.PosteVacantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteVacantServiceImpl implements PosteVacantService {

    @Autowired
    private PosteVacantRepository posteVacantRepository;

    @Override
    public PosteVacant save(PosteVacant poste){
        return posteVacantRepository.save(poste);
    }

    @Override
    public List<PosteVacant> getAll(){
        return posteVacantRepository.findAll();
    }

    @Override
    public PosteVacant getById(Integer id)throws WsException {
        Optional<PosteVacant> optional = posteVacantRepository.findById(id);
        PosteVacant poste;
        if (optional.isPresent()){
            poste = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return poste;
    }


    @Override
    public void delete(PosteVacant poste){
        posteVacantRepository.delete(poste);
    }


    @Override
    public PosteVacant update(Integer id, PosteVacant poste){
        PosteVacant poste1 = this.getById(id);
        poste1.setId(poste.getId());
        return posteVacantRepository.save(poste1);
    }
}
