package getionRh.example.rh.entity;


import getionRh.example.rh.enumerate.SituationFamilialeEnum;
import jakarta.persistence.*;
import lombok.Data;

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


    private Date dateDeNaissance;


    private String email;


    @Column(nullable = false)
    private String telephone;

    private String adresse;

    @Column(nullable = true)
    private int cp;

    @Column(nullable = true)
    private String ville;


    private int nombreEnfant;


   @ManyToOne()
    private Pays pays;



    @ManyToOne
    private Civilite civilite;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private SituationFamilialeEnum situationFamiliale;
}
