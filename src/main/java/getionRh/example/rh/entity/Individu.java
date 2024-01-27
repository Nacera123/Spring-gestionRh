package getionRh.example.rh.entity;


import getionRh.example.rh.enumerate.EtatCivilEnum;
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

    @Column(name = "date_de_naissance")
    private Date dateDeNaissance;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    private String adresse;

    @Column(nullable = true)
    private int cp;

    private String ville;

    @Column(name = "nombre_enfant")
    private int nombreEnfant;


   @ManyToOne
    private Pays pays;


    @Column(name = "etat_civil")
    @Enumerated(EnumType.STRING)
    private EtatCivilEnum etatCivilEnum;

    @Column(name = "situation_familiale")
    @Enumerated(EnumType.STRING)
    private SituationFamilialeEnum situationFamiliale;
}
