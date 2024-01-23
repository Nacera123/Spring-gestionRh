package getionRh.example.rh.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.model.entity.Candidat;
import getionRh.example.rh.model.entity.Individu;
import lombok.Data;


@Data
public class CandidatDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("individu")
    private IndividuDto individu;

    public CandidatDto(Candidat candidat){

        id = candidat.getId();
        individu = new IndividuDto(candidat.getIndividu());
    }

}
