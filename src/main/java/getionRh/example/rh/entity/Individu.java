package getionRh.example.rh.entity;


import getionRh.example.rh.enumerate.EtatCivilEnum;
import getionRh.example.rh.enumerate.SituationFamilialeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Individu {

//    public Individu(){
//        this.nom = "";
//        this.prenom = "";
//        this.email = "";
//        this.telephone = "";
//    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;


    @Column(nullable = false)
    private String prenom;

    @Column(name = "date_de_naissance", nullable = true)
    private Date dateDeNaissance;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    private String adresse;

    @Column(nullable = true)
    private int cp;

    @Column(nullable = true)
    private String ville;

    @Column(name = "nombre_enfant", nullable = true)
    private int nombreEnfant;


   @ManyToOne()
    private Pays pays;


    @Column(name = "etat_civil", nullable = true)
    @Enumerated(EnumType.STRING)
    private EtatCivilEnum etatCivilEnum;

    @Column(name = "situation_familiale", nullable = true)
    @Enumerated(EnumType.STRING)
    private SituationFamilialeEnum situationFamiliale;
}
