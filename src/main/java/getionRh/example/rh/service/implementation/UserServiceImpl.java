package getionRh.example.rh.service.implementation;

import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.repository.UserRepository;
import getionRh.example.rh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;



    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public boolean isTokenExist(String token){
        return userRepository.countByTokenLike(token) > 0;
    }




    /**
     * Recupere le user grace a son Token
     * @param token
     * @return
     */
    @Override
    public User findByToken(String token){
        List<User> users = userRepository.findByToken(token);
        if (users.size() != 1){
            return null;
        }
        return users.get(0);
    }

    @Override
    public UserDetails loadByUsername(String username)throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findByEmailIgnoreCase(username);
        if (userDetails == null){
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }

}
