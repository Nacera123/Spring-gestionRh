package getionRh.example.rh.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Candidat {


    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    private Individu individu;

}
