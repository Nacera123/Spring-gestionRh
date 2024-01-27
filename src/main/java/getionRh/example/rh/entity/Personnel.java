package getionRh.example.rh.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Personnel {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String matricule;

    @OneToOne
    private Individu individu;

}
