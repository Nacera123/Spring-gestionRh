package getionRh.example.rh.controller.serurity;


import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.Role;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.manager.JwtTokenGenerater;
import getionRh.example.rh.manager.TokenGenerater;
import getionRh.example.rh.service.implementation.IndividuServiceImpl;
import getionRh.example.rh.service.implementation.RoleServiceImpl;
import getionRh.example.rh.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IndividuServiceImpl individuService;

    @PostMapping("/register")
    public Map<String, String> regiter(@RequestBody User user) throws Exception{
        //1- Verifier si l'email existe
//        try {
//            userService.loadByUsername(user.getEmail());
//            throw new WsException(HttpStatus.BAD_REQUEST, "Email existe deja");
//        }catch (UsernameNotFoundException ignored){
//        }


        Individu individu = user.getIndividu(); // tout les donnée envoyer ...
        individu.setEmail(user.getEmail());
        individuService.save(user.getIndividu());

        //2- Enregistrer le info du user
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setIndividu(individu);


        Role role = roleService.findByName("CANDIDAT").orElse(null);


        newUser.setRoles(role);

        //3- Enregistrer le user
        newUser.setToken(TokenGenerater.generateToken(userService));
        userService.save(newUser);




        Map<String , String> response = new HashMap<>();
        response.put("token", JwtTokenGenerater.generateToken(newUser.getToken()));
        //4- Recuperer sa token 
        return response;

    }

}
