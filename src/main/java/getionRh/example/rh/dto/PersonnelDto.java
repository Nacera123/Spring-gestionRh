package getionRh.example.rh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.entity.Personnel;
import lombok.Data;


@Data
public class PersonnelDto {

    @JsonProperty("id")
    private Integer id;


    @JsonProperty("matricule")
    private String matricule;

    @JsonProperty("individu")
    private IndividuDto individu;



//    @JsonProperty("situationFamiliale")
//    private SituationFamilialeDto situationFamiliale;


    public PersonnelDto(Personnel personnel){
        id = personnel.getId();
        matricule = personnel.getMatricule();
        individu = new IndividuDto(personnel.getIndividu());
//        situationFamiliale = new SituationFamilialeEnum(personnel.)
//        situationFamiliale = new SituationFamilialeDto(personnel.getSituationFamiliale());
    }

}
