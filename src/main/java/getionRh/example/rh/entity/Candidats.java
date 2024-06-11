package getionRh.example.rh.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Candidats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Individu individu;

}
