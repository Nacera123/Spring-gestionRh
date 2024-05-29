package getionRh.example.rh.entity.candidature;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class DocumentCandidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pieceJointe; // 09887777766.pdf

    private String nomFichier;

    @ManyToOne
    private NomDocument nomPieceJointe;

    @ManyToOne
    private Candidature candidature;
}
