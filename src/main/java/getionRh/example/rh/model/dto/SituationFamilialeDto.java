package getionRh.example.rh.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.model.entity.Personnel;
import getionRh.example.rh.model.entity.SituationFamiliale;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class SituationFamilialeDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("situation")
    private String situation;

    @JsonProperty("personnels")
    List<Integer> personnels;


    public SituationFamilialeDto(SituationFamiliale situationFamiliale){
        id = situationFamiliale.getId();
        situation = situationFamiliale.getSituation();

        List<Integer> personnelList = new ArrayList<>();
        if (situationFamiliale.getPersonnels() != null && !situationFamiliale.getPersonnels().isEmpty()){
            for (Personnel pr : situationFamiliale.getPersonnels()){
                personnelList.add(pr.getId());
            }
        }
        personnels = personnelList;
    }

}
