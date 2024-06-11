package getionRh.example.rh.service.implementation.candidature;



import getionRh.example.rh.entity.candidature.PosteVacant;
import getionRh.example.rh.entity.candidature.SessionCandidature;
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
    public PosteVacant save(PosteVacant poste)throws Exception{

        if (poste.getPoste().getNom() ==null ){
            throw new Exception("Veuillez ajouter la reference du poste de travail");
        }
        if (poste.getSession().getReference() == null ){
            throw new Exception("veuillez remplir une refere valide pour le poste a pourvoir");
        }
        if (poste.getDescriptif() == null || poste.getDescriptif().isEmpty()){
            throw new Exception("Veuillez remplir un descriptif valide pour le poste a pourvoir");
        }
        if (poste.getNom() == null || poste.getNom().isEmpty()){
            throw new Exception("Veuillez remplir un nom valide pour le poste a pourvoir");
        }

        return posteVacantRepository.save(poste);
    }

    @Override
    public List<PosteVacant> getAll()throws WsException{
        List<PosteVacant> posteVacants =  posteVacantRepository.findAll();


        if (posteVacants.isEmpty()){
            throw new WsException(HttpStatus.NOT_FOUND, "la liste des postes vacants est vide");
        }else {
            return posteVacants;
        }

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
    public PosteVacant update(Integer id, PosteVacant poste)throws Exception{
        PosteVacant poste1 = this.getById(id);
        poste1.setPoste(poste.getPoste());
        poste1.setDescriptif(poste.getDescriptif());
        poste1.setSession(poste.getSession());
        poste1.setTypeContrat(poste.getTypeContrat());
        return this.save(poste1);
    }
}
