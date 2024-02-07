package getionRh.example.rh.service;


import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    public boolean isTokenExist(String token);
    public User findByToken(String token);


}
