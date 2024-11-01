package getionRh.example.rh.service.implementation.candidature;

import getionRh.example.rh.entity.candidature.Candidature;
import getionRh.example.rh.entity.candidature.SessionCandidature;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.candidature.CandidatureRepository;
import getionRh.example.rh.service.candidature.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatureServiceImpl implements CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Override
    public Candidature save(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public List<Candidature> getAll() throws WsException {

        List<Candidature> candidatures = candidatureRepository.findAll();
        if (candidatures.isEmpty()) {
            throw new WsException(HttpStatus.NOT_FOUND, "La liste de candidature est vide");
        } else {
            return candidatures;
        }

    }

    @Override
    public Candidature getById(Integer id) throws WsException {
        Optional<Candidature> optional = candidatureRepository.findById(id);
        Candidature candidature;
        if (optional.isPresent()) {
            candidature = optional.get();
        } else {
            throw new WsException(HttpStatus.NOT_FOUND, "⚠ La candidature " + id + " n'existe pas!");
        }
        return candidature;
    }

    @Override
    public void delete(Integer id) {

        candidatureRepository.deleteById(id);
    }

    @Override
    public Candidature update(Integer id, Candidature candidature) {
        Candidature candidature1 = this.getById(id);
        candidature1.setEtatCandidature(candidature.getEtatCandidature());
        candidature1.setIndividu(candidature.getIndividu());
        candidature1.setPosteVacant(candidature.getPosteVacant());
        return this.save(candidature1);
    }

    public List<Candidature> candidatureBySession(SessionCandidature sessionCandidature) {
        List<Candidature> candidatures = sessionCandidature != null
                ? candidatureRepository.findCandidatureBySession(sessionCandidature)
                : null;
        return candidatures != null && !candidatures.isEmpty() ? candidatures : new ArrayList<>();
    }

    public List<Candidature> candidatureParCandidats(Integer id) {
        List<Candidature> candidature = candidatureRepository.findCandidaturesByIndividu_Id(id);
        return candidature;
    }
    public List<Candidature> test(Integer id) {
        List<Candidature> candidature = candidatureRepository.findByIndividu_Id(id);
        return candidature;
    }

    public List<Candidature> getByposteVacant(String nom){

        List<Candidature> candidature = candidatureRepository.findCandidaturesByPosteVacant_Nom(nom);
        return candidature != null && !candidature.isEmpty() ? candidature : new ArrayList<>();
    }

    public static String candidatueVsPosteVacant(int nbPosteVcant, int candidatureAccepte){
        if (nbPosteVcant < candidatureAccepte){
            return  "attention !  il y'a plus de candidats acceptés qu'il y'a de postes vacants";
        }else if (nbPosteVcant == candidatureAccepte){
            return  "Bon travail ! vous avez recruté les bons candidats";
        }else if (nbPosteVcant > candidatureAccepte){
            return  "Cette année il y'a eu trop de chomage! ";
        }
        return "les parametres ne sont pas clairs";
    }
}
