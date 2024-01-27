package getionRh.example.rh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.entity.Individu;
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


    @JsonProperty("pays")
    private PaysDto pays;

    @JsonProperty("situation_familiale")
    private String situationFamiliale;
    @JsonProperty("etat_civil")
    private String etatCivilEnum;
//    @JsonProperty("situation_familiale")
//    private SituationFamilialeEnum situationFamiliale;
//    @JsonProperty("etat_civil")
//    private EtatCivilEnum etatCivilEnum;



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
        etatCivilEnum = individu.getEtatCivilEnum()==null ? "":individu.getEtatCivilEnum().getAbreviation();
        pays = new PaysDto(individu.getPays());
        situationFamiliale = individu.getSituationFamiliale()==null? "":individu.getSituationFamiliale().toString();
    }

}
