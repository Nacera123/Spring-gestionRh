package getionRh.example.rh.dto;


import getionRh.example.rh.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationDto {

    private String email;
    private String password;


    public AuthenticationDto(User user){
        email = user.getEmail();
        password = user.getPassword();
    }

}
