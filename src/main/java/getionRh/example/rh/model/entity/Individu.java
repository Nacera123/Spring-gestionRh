package getionRh.example.rh.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
public class Individu {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;


    @Column(nullable = false)
    private String prenom;

    @Column(name = "date_de_naissance")
    private Date dateDeNaissance;

    private String email;

    private String telephone;

    private String adresse;

    private int cp;

    private String ville;

    @Column(name = "nombre_enfant")
    private int nombreEnfant;
}
