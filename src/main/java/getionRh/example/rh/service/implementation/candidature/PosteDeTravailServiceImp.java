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
    public PosteDeTravail save(PosteDeTravail poste)throws Exception{

        if (poste.getNom() == "" || poste.getNom().isEmpty()){
            throw new Exception("Veuillez remplir un nom de poste valide  ");
        }
        if (poste.getReference() == ""|| poste.getReference().isEmpty()){
            throw new Exception("Veuillez remplir un nom de poste valide  ");
        }
        if (posteDeTravailRepository.existsByReferenceIgnoreCase(poste.getReference())){
            throw new Exception("cette reference existe deja. Veuillez choisir une autre reference");
        }
        return posteDeTravailRepository.save(poste);
    }

    @Override
    public List<PosteDeTravail> getAll()throws  WsException{
        List<PosteDeTravail> posteDeTravails = posteDeTravailRepository.findAll();
        if(posteDeTravails.isEmpty()){
            throw new WsException(HttpStatus.NOT_FOUND, "la liste des poste est vide");
        }else {

                return posteDeTravails;
        }

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
    public void deletePoste(PosteDeTravail poste){
        posteDeTravailRepository.delete(poste);
    }
    public void  delete(Integer id){
            posteDeTravailRepository.deleteById(id);
    }

    @Override
    public PosteDeTravail update(Integer id, PosteDeTravail poste)throws Exception{
        PosteDeTravail poste1 = this.getById(id);
        poste1.setNom(poste.getNom());
        poste1.setReference(poste.getReference());
        return this.save(poste1);
    }

    @Override
    public PosteDeTravail getByNom(String nom)throws Exception{
        Optional<PosteDeTravail> optional = Optional.ofNullable(posteDeTravailRepository.findByNomIgnoreCase(nom));
        PosteDeTravail poste;
        if (optional.isPresent()){
            poste = optional.get();
        }else {
            throw new Exception("Le poste " + nom + " n'existe pas");
        }
        return poste;
    }


}
