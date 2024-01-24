package getionRh.example.rh.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class SituationFamiliale {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String situation;

    @OneToMany(mappedBy = "situationFamiliale")
    List<Personnel> personnels;
}
