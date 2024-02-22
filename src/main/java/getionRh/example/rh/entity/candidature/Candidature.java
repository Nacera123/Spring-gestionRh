package getionRh.example.rh.entity.candidature;


import getionRh.example.rh.entity.Individu;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Individu individu;

    @ManyToOne
    private EtatCandidature etatCandidature;

    @ManyToOne
    private PosteVacant poste;
}
