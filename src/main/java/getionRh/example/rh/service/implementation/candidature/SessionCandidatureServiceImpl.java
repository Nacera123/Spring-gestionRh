package getionRh.example.rh.service.implementation.candidature;

import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.SessionCandidatureRepository;
import getionRh.example.rh.service.candidature.SessionCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionCandidatureServiceImpl implements SessionCandidatureService {

    @Autowired
    private SessionCandidatureRepository sessionCandidatureRepository;

    @Override
    public SessionCandidature save(SessionCandidature session){

        return sessionCandidatureRepository.save(session);
    }

    @Override
    public List<SessionCandidature> getAll() throws WsException{

        List<SessionCandidature> sessionCandidatures = sessionCandidatureRepository.findAll();

        if (sessionCandidatures.isEmpty()){
            throw new WsException(HttpStatus.NOT_FOUND, "la liste des sessions est vide");
        }else {
            return sessionCandidatures;
        }

    }

    @Override
    public SessionCandidature getById(Integer id)throws WsException {
        Optional<SessionCandidature> optional = sessionCandidatureRepository.findById(id);
        SessionCandidature session;
        if (optional.isPresent()){
            session = optional.get();
        }else {
            throw new WsException(HttpStatus.NOT_FOUND, "Le document "+id+ " n'existe pas");
        }
        return session;
    }

    @Override
    public void delete(SessionCandidature session){
        sessionCandidatureRepository.delete(session);
    }


    @Override
    public SessionCandidature update(Integer id, SessionCandidature session){
        SessionCandidature session1 = this.getById(id);
        session1.setStatus(session.getStatus());
        session1.setDateCloture(session.getDateCloture());
        session1.setDateOuverture(session.getDateOuverture());
        session1.setReference(session.getReference());
        return this.save(session1);
    }
}
