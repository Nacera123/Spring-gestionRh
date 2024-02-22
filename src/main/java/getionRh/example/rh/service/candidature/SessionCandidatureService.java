package getionRh.example.rh.service.candidature;

import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;

import java.util.List;

public interface SessionCandidatureService {
    SessionCandidature save(SessionCandidature session);

    List<SessionCandidature> getAll();

    SessionCandidature getById(Integer id)throws WsException;

    void delete(SessionCandidature session);

    SessionCandidature update(Integer id, SessionCandidature session);
}
