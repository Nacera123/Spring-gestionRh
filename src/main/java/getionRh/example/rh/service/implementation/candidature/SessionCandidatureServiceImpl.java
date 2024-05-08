package getionRh.example.rh.service.implementation.candidature;

import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.SessionCandidatureRepository;
import getionRh.example.rh.service.candidature.SessionCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SessionCandidatureServiceImpl implements SessionCandidatureService {

    @Autowired
    private SessionCandidatureRepository sessionCandidatureRepository;

    @Override
    public SessionCandidature save(SessionCandidature session) throws Exception{

        Date dateActuelle = new Date();

        if (session.getReference() == null || session.getReference().isEmpty()){
           throw new Exception("Veuillez remplir une reference ");
        }
        if (sessionCandidatureRepository.existsByReferenceIgnoreCase(session.getReference())){
            throw new Exception("Cette reference existe deja");
        }

        if (session.getDateOuverture() == null || session.getDateOuverture().equals(new Date(0))) {
            throw new Exception("Veuillez spécifier une date d'ouverture.");
        } else if (session.getDateOuverture().before(dateActuelle)) {
            throw new Exception("La date d'ouverture ne peut pas être antérieure à aujourd'hui.");
        }
        if (session.getDateCloture() == null || session.getDateCloture().equals(new Date(0))) {
            throw new Exception("Veuillez spécifier une date de clôture.");
        } else if (session.getDateCloture().before(session.getDateOuverture())) {
            throw new Exception("La date de clôture ne peut pas être antérieure à la date d'ouverture.");
        }




        return sessionCandidatureRepository.save(session);
    }


    @Override
    public List<SessionCandidature> getAll() throws Exception{

        List<SessionCandidature> sessionCandidatures = sessionCandidatureRepository.findAll();

        if (sessionCandidatures.isEmpty()){
            throw new Exception("la liste des sessions est vide");
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
    public void delete(Integer id){
        sessionCandidatureRepository.deleteById(id);
    }


    @Override
    public SessionCandidature update(Integer id, SessionCandidature session)throws Exception{
        Date dateActuelle = new Date();
        SessionCandidature session1 = this.getById(id);
        session1.setStatus(session.getStatus());
        session1.setDateCloture(session.getDateCloture());
        session1.setDateOuverture(session.getDateOuverture());
        session1.setReference(session.getReference());


        SessionCandidature reference = sessionCandidatureRepository.findByReference(session.getReference());
        if (session1.getReference() == null || session1.getReference().isEmpty()){
            throw new Exception("Veuillez remplir une reference ");
        }
        if (reference != null && !reference.getId().equals(session1.getId())) {
            throw new Exception("Cette référence existe déjà");
        }
        if (session1.getDateOuverture() == null || session1.getDateOuverture().equals(new Date(0))) {
            throw new Exception("Veuillez spécifier une date d'ouverture.");
        } else if (session1.getDateOuverture().before(dateActuelle)) {
            throw new Exception("La date d'ouverture ne peut pas être antérieure à aujourd'hui.");
        }
        if (session1.getDateCloture() == null || session1.getDateCloture().equals(new Date(0))) {
            throw new Exception("Veuillez spécifier une date de clôture.");
        } else if (session1.getDateCloture().before(session1.getDateOuverture())) {
            throw new Exception("La date de clôture ne peut pas être antérieure à la date d'ouverture.");
        }

       // return this.save(session1);
        return sessionCandidatureRepository.save(session1);
    }


    @Override
    public SessionCandidature getByReference(String reference)throws Exception{
        Optional<SessionCandidature> optional = Optional.ofNullable(sessionCandidatureRepository.findByReferenceIgnoreCase(reference));
        SessionCandidature session;
        if (optional.isPresent()){
            session = optional.get();
        }else {
            throw new Exception("La reference " + reference + " n'existe pas");
        }
        return session;
    }
}
