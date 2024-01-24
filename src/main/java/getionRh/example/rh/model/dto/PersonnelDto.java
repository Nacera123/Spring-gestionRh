package getionRh.example.rh.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.model.entity.Individu;
import getionRh.example.rh.model.entity.Personnel;
import getionRh.example.rh.model.entity.SituationFamiliale;
import jakarta.persistence.*;
import lombok.Data;


@Data
public class PersonnelDto {

    @JsonProperty("id")
    private Integer id;


    @JsonProperty("matricule")
    private String matricule;

    @JsonProperty("individu")
    private IndividuDto individu;

    @JsonProperty("situationFamiliale")
    private SituationFamilialeDto situationFamiliale;


    public PersonnelDto(Personnel personnel){
        id = personnel.getId();
        matricule = personnel.getMatricule();
        individu = new IndividuDto(personnel.getIndividu());
        situationFamiliale = new SituationFamilialeDto(personnel.getSituationFamiliale());
    }

}
