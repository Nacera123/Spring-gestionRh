package getionRh.example.rh.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {

    public User(){
        this.dateCreation = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^|([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)", message = "Invalid email")
    private String email;

    private String password;
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    private boolean active;

    @ManyToOne
    private Role roles;

    @OneToOne
    private Individu individu;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.roles);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
