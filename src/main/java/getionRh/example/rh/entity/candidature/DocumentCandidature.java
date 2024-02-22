package getionRh.example.rh.entity.candidature;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DocumentCandidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pieceJointe;

    @ManyToOne
    private NomDocument nomPieceJointe;

    @ManyToOne
    private Candidature candidature;
}
