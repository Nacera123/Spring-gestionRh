package getionRh.example.rh.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Pays {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String designation;

    private String abreviation;

    @OneToMany(mappedBy = "pays")
    private List<Individu> country;
}
