package getionRh.example.rh.data;



import getionRh.example.rh.entity.Role;
import getionRh.example.rh.service.implementation.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements ApplicationRunner {


    @Autowired
    private RoleServiceImpl roleService;



    @Override
    public void run(ApplicationArguments args) throws Exception {
            if (roleService.count() == 0){
                String[] lesroles ={
                        "CANDIDAT",
                        "PERSONNEL",
                        "ADMIN",
                        "SUPER_ADMIN"
                };
                for (String role : lesroles){
                    Role role1 = new Role();
                    role1.setNom(role);
                    roleService.save(role1);
                }
            }
    }

}
