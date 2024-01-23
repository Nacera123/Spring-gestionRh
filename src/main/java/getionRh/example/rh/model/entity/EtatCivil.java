package getionRh.example.rh.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class EtatCivil {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String designation;

    private String abreviation;


    @OneToMany(mappedBy = "civil")
    private List<Individu> civilites;
}
