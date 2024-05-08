package getionRh.example.rh.entity.candidature;

import com.fasterxml.jackson.annotation.JsonFormat;
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


    //@Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date dateOuverture;


    //@Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date dateCloture;

    private Boolean status;
}
