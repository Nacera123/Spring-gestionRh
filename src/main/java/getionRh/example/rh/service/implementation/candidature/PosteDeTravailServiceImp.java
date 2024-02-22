package getionRh.example.rh.service.implementation.candidature;


import getionRh.example.rh.entity.candidature.PosteDeTravail;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.PosteDeTravailRepository;
import getionRh.example.rh.service.candidature.PosteDeTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteDeTravailServiceImp implements PosteDeTravailService {


    @Autowired
    private PosteDeTravailRepository posteDeTravailRepository;

    @Override
    public PosteDeTravail save(PosteDeTravail poste){
        return posteDeTravailRepository.save(poste);
    }

    @Override
    public List<PosteDeTravail> getAll(){
        return posteDeTravailRepository.findAll();
    }

    @Override
    public PosteDeTravail getById(Integer id)throws WsException {
        Optional<PosteDeTravail> optional = posteDeTravailRepository.findById(id);
        PosteDeTravail poste;
        if (optional.isPresent()){
            poste = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return poste;
    }

    @Override
    public void delete(PosteDeTravail poste){
        posteDeTravailRepository.delete(poste);
    }

    @Override
    public PosteDeTravail update(Integer id, PosteDeTravail poste){
        PosteDeTravail poste1 = this.getById(id);
        poste1.setNom(poste.getNom());
        poste1.setReference(poste.getReference());
        return this.save(poste1);
    }


}
