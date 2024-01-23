package getionRh.example.rh.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.model.entity.Individu;
import getionRh.example.rh.model.entity.Personnel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
public class PersonnelDto {

    @JsonProperty("id")
    private Integer id;


    @JsonProperty("matricule")
    private String matricule;

    @JsonProperty("individu")
    private IndividuDto individu;

    public PersonnelDto(Personnel personnel){
        id = personnel.getId();
        matricule = personnel.getMatricule();
        individu = new IndividuDto(personnel.getIndividu());
    }

}
