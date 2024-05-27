package getionRh.example.rh.data;


import getionRh.example.rh.entity.Civilite;
import getionRh.example.rh.service.implementation.CiviliteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CiviliteInitializer implements ApplicationRunner {

    @Autowired
    private CiviliteServiceImpl civiliteService;



    @Override
    public void run(ApplicationArguments args) throws Exception{
        System.out.println("Initializing civilite");

        if (this.civiliteService.count() == 0){
            String[][] civiliteData = {
                    {"Madame", "Mme"},
                    {"Mademoiselle", "Mlle"},
                    {"Monsieur", "Mr"},
            };
            for (String[] civiliteInfo: civiliteData){
                Civilite civilite = new Civilite();
                civilite.setDesignation(civiliteInfo[0]);
                civilite.setAbreviation(civiliteInfo[1]);
                civiliteService.save(civilite);
            }
        }
        System.out.println("civilite data completed");


    }
}
