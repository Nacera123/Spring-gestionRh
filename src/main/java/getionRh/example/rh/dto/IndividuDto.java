package getionRh.example.rh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.entity.Civilite;
import getionRh.example.rh.entity.Individu;
import lombok.Data;

import java.sql.Date;

/*
{"individu":{"nom":"x","prenom":"xx","email":"xxx","telephone":"xxxx","pays":{"id":1,"nom":"cv"},"civilite":{"id":19,"designation":"BANGLADESH","abreviation":"880"}}}
 */
@Data
public class IndividuDto {

    public IndividuDto(){

    }

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
    @JsonProperty("civilite")
    private Civilite civilite;
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


        ///etatCivilEnum = individu.getEtatCivilEnum()==null ? "":individu.getEtatCivilEnum().getAbreviation();
        //etatCivilEnum = individu.getCivilite().getDesignation();
        pays = new PaysDto(individu.getPays());
        situationFamiliale = individu.getSituationFamiliale()==null? "":individu.getSituationFamiliale().toString();
    }

}
