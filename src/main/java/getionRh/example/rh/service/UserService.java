package getionRh.example.rh.service;



import getionRh.example.rh.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    public boolean isTokenExist(String token);
    public User findByToken(String token);

    public UserDetails loadByUsername(String username);

    public User save(User user);

    User loadUserByRole(String nom);
}
