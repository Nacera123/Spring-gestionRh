package getionRh.example.rh.controller.serurity;


import getionRh.example.rh.dto.AuthenticationDto;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.manager.JwtTokenGenerater;
import getionRh.example.rh.service.UserService;
import getionRh.example.rh.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationDto authenticationDto){

        UserDetails userDetails = userService.loadByUsername(authenticationDto.getEmail());

        if (!passwordEncoder.matches(authenticationDto.getPassword(), userDetails.getPassword())){
            throw new WsException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");

        }

        User user = (User) userDetails;
        return JwtTokenGenerater.generateToken(user.getToken());
    }
























}
