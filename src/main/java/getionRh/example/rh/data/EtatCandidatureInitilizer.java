package getionRh.example.rh.data;


import getionRh.example.rh.entity.candidature.EtatCandidature;
import getionRh.example.rh.service.implementation.candidature.EtatCandidatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EtatCandidatureInitilizer implements ApplicationRunner {

    @Autowired
    private EtatCandidatureServiceImpl etatCandidatureService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(etatCandidatureService.count() == 0){
            String[] etat = {
                    "Candidature envoyée",
                    "Candidature consultée par le recruteur",
                    "Candidature  En cours de traitement",
                    "Candidature  Refusée",
                    "Candidature  Acceptée"
            };
            for (String etats : etat){
                EtatCandidature etat1 = new EtatCandidature();
                etat1.setEtat(etats);
                etatCandidatureService.save(etat1);
            }
        }
    }
}
