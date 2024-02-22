package getionRh.example.rh.data;

import getionRh.example.rh.entity.candidature.TypeDeContrat;
import getionRh.example.rh.service.implementation.candidature.TypeDeContratServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class ContratInitializer implements ApplicationRunner {

    @Autowired
    private TypeDeContratServiceImpl typeDeContratService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (typeDeContratService.count() == 0){
            String[] contrat = {
                    "CDI",
                    "CDD",
                    "Intérim",
                    "Stage",
                    "Alternance",
                    "Freelance"
            };
            for (String contrats: contrat){
                TypeDeContrat contrat1 = new TypeDeContrat();
                contrat1.setType(contrats);
                typeDeContratService.save(contrat1);
            }
        }
    }
}
