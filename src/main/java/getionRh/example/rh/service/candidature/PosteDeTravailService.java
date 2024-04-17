package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.PosteDeTravail;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface PosteDeTravailService {
    PosteDeTravail save(PosteDeTravail poste);

    List<PosteDeTravail> getAll();

    PosteDeTravail getById(Integer id)throws WsException;

    //void delete(PosteDeTravail poste);

    void deletePoste(PosteDeTravail poste);

    //    public void delete(PosteDeTravail poste){
//        posteDeTravailRepository.delete(poste);
//    }
    void  delete(Integer id);

    PosteDeTravail update(Integer id, PosteDeTravail poste);
}
