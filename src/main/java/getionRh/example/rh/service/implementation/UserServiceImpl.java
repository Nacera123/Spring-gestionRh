package getionRh.example.rh.service.implementation;

import getionRh.example.rh.entity.Individu;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.repository.UserRepository;
import getionRh.example.rh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(User user) throws WsException {
        // Vérification des champs email et mot de passe vides
        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new WsException(HttpStatus.BAD_REQUEST, "Veuillez remplir tous les champs.");
        }

        // Vérification si l'email existe déjà
        if (userRepository.existsByEmailLikeIgnoreCase(user.getEmail())) {
            throw new WsException(HttpStatus.BAD_REQUEST, "L'email existe déjà.");
        }

        // Validation de l'email
        if (!user.getEmail().matches(".+@.+\\.[a-z]+")) {
            throw new WsException(HttpStatus.BAD_REQUEST, "Entrez un email valide : " + user.getEmail());
        }

        // Validation du mot de passe
        String password = user.getPassword();

        if (password == null || password.isEmpty()) {
            throw new WsException(HttpStatus.BAD_REQUEST, "Veuillez saisir un mot de passe.");
        }
        if (password.length() < 6) {
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 6 caractères.");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins une majuscule.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins un chiffre.");
        }

        return userRepository.save(user);
    }

    public User test(User user) throws WsException {
        // Vérification des champs email et mot de passe vides
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new WsException(HttpStatus.BAD_REQUEST, "Veuillez remplir tous les champs.");
        }


        return userRepository.save(user);
    }

    @Override
    public boolean isTokenExist(String token) {
        return userRepository.countByTokenLike(token) > 0;
    }

    /**
     * Recupere le user grace a son Token
     * 
     * @param token
     * @return
     */
    @Override
    public User findByToken(String token) {
        List<User> users = userRepository.findByToken(token);
        if (users.size() != 1) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public UserDetails loadByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findByEmailIgnoreCase(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }

    @Override
    public User loadUserByRole(String nom) {
        return userRepository.findByRoles_NomIgnoreCase(nom);
    }

    public User addUserFromIndividu(User user) {
        return userRepository.save(user);
    }

    public User loadByIdIndividu(Integer id) {

        return userRepository.findByIndividu_Id(id);

    }

}
