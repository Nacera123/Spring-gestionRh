package getionRh.example.rh.controller;

import getionRh.example.rh.entity.Role;
import getionRh.example.rh.entity.User;
import getionRh.example.rh.exception.WsException;
import getionRh.example.rh.service.implementation.RoleServiceImpl;
import getionRh.example.rh.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {

        UserDetails userDetails = userService.loadByUsername(email);
        User user = (User) userDetails;
        return user;

    }

    @GetMapping("/role/{email}")

    public ResponseEntity<Map<String, String>> getRole(@PathVariable String email) {
        UserDetails userDetails = userService.loadByUsername(email);

        if (userDetails instanceof User) {
            User user = (User) userDetails;
            String role = user.getRoles().getNom();
            Map<String, String> response = Collections.singletonMap("role", role);
            return ResponseEntity.ok(response);
        } else {
            // Gestion si l'utilisateur n'est pas trouvé ou n'est pas une instance de User
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/a/{a}")
    public ResponseEntity<?> getInd(@RequestParam int id) {
        User userDetails = userService.loadByIdIndividu(id);
        return ResponseEntity.ok(userDetails);

    }

    @GetMapping("/bb/{id}")
    public ResponseEntity<Map<String, Integer>> gettoto(@PathVariable Integer id) {
        UserDetails userDetails = userService.loadByIdIndividu(id);

        if (userDetails instanceof User) {
            User user = (User) userDetails;
            Integer individu = user.getIndividu().getId();
            Map<String, Integer> response = Collections.singletonMap("individu", individu);
            return ResponseEntity.ok(response);
        } else {
            // Gestion si l'utilisateur n'est pas trouvé ou n'est pas une instance de User
            return ResponseEntity.notFound().build();
        }
    }

}
