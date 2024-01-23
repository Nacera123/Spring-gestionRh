package getionRh.example.rh.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.model.entity.EtatCivil;
import getionRh.example.rh.model.entity.Individu;
import getionRh.example.rh.model.entity.Pays;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Data
public class IndividuDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("prenom")
    private String prenom;

    @JsonProperty("dateDeNaissance")
    private Date dateDeNaissance;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("cp")
    private int cp;

    @JsonProperty("ville")
    private String ville;

    @JsonProperty("nombreEnfant")
    private int nombreEnfant;

    @JsonProperty("civil")
    private EtatCivilDto civil;

    @JsonProperty("pays")
    private PaysDto pays;


    public IndividuDto(Individu individu){
        id = individu.getId();
        nom = individu.getNom();
        prenom = individu.getPrenom();
        dateDeNaissance = individu.getDateDeNaissance();
        email = individu.getEmail();
        telephone = individu.getTelephone();
        adresse = individu.getAdresse();
        cp = individu.getCp();
        ville = individu.getVille();
        nombreEnfant = individu.getNombreEnfant();
        civil = new EtatCivilDto(individu.getCivil());
        pays = new PaysDto(individu.getPays());
    }

}
