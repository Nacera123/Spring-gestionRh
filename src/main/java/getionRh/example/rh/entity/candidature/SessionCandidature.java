package getionRh.example.rh.entity.candidature;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Entity
@Getter
public class SessionCandidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reference;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOuverture;


    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCloture;

    private Boolean status;
}
