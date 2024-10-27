package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.PosteVacant;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface PosteVacantService {
    PosteVacant save(PosteVacant poste) throws Exception;

    List<PosteVacant> getAll();

    PosteVacant getById(Integer id)throws WsException;

    void  delete(Integer id);

    PosteVacant update(Integer id, PosteVacant poste) throws Exception;
}
