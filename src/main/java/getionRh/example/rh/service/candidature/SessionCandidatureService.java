package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface SessionCandidatureService {
    SessionCandidature save(SessionCandidature session) throws Exception;

    //List<SessionCandidature> getAll();

    List<SessionCandidature> getAll() throws Exception;

    SessionCandidature getById(Integer id)throws WsException;



    void delete(Integer id);

    SessionCandidature update(Integer id, SessionCandidature session) throws Exception;

    SessionCandidature getByReference(String reference)throws Exception;
}
