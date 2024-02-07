package getionRh.example.rh.controller.serurity;


import getionRh.example.rh.entity.Role;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.manager.JwtTokenGenerater;
import getionRh.example.rh.manager.TokenGenerater;
import getionRh.example.rh.service.implementation.RoleServiceImpl;
import getionRh.example.rh.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String regiter(@RequestBody User user){
        //1- Verifier si l'email existe
        try {
            userService.loadByUsername(user.getEmail());
            return "user already exist";
        }catch (Exception e){

        }

        //2- Enregistrer le info du user
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setActive(true);

        Role role = new Role();
        role.setNom("CANDIDAT");
        newUser.setRoles(role);

        //3- Enregistrer le user
        newUser.setToken(TokenGenerater.generateToken(userService));
        userService.save(newUser);

        //4- Recuperer sa token 
        return JwtTokenGenerater.generateToken(newUser.getToken());

    }

}
