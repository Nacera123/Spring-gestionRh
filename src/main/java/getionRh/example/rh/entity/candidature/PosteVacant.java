package getionRh.example.rh.entity.candidature;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class PosteVacant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @Column(name = "descriptif", columnDefinition = "TEXT")
    private String descriptif;

    @ManyToOne
    private SessionCandidature session;

    @ManyToOne
    private PosteDeTravail poste;

    @ManyToOne
    private TypeDeContrat typeContrat;
}
