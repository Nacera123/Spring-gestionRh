package getionRh.example.rh.controller.serurity;

import getionRh.example.rh.dto.AuthenticationDto;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.manager.JwtTokenGenerater;
import getionRh.example.rh.service.UserService;
import getionRh.example.rh.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthenticationDto authenticationDto) {

        UserDetails userDetails = userService.loadByUsername(authenticationDto.getEmail());

        if (!passwordEncoder.matches(authenticationDto.getPassword(), userDetails.getPassword())) {
            throw new WsException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");

        }

        User user = (User) userDetails;
        Map<String, String> response = new HashMap<>();
        response.put("token", JwtTokenGenerater.generateToken(user.getToken()));

        return response;
    }

    @PostMapping("/login1")
    public Map<String, Object> login1(@RequestBody AuthenticationDto authenticationDto) {

        UserDetails userDetails = userService.loadByUsername(authenticationDto.getEmail());

        if (!passwordEncoder.matches(authenticationDto.getPassword(), userDetails.getPassword())) {
            throw new WsException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
        }

        User user = (User) userDetails;
        Map<String, Object> response = new HashMap<>();
        response.put("token", JwtTokenGenerater.generateToken(user.getToken()));
        response.put("id", user.getId()); // Ajout de l'ID de l'utilisateur à la réponse
        response.put("id_individu", user.getIndividu().getId()); // Ajout de l'ID de l'utilisateur à la réponse

        return response;
    }

}
