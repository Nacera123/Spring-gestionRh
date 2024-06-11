package getionRh.example.rh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.entity.Candidat;
import lombok.Data;


@Data
public class CandidatDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("individu")
    private IndividuDto individu;

    private Integer individu1;

    public CandidatDto(Candidat candidat){

        id = candidat.getId();
        individu = new IndividuDto(candidat.getIndividu());
        this.individu1 = candidat.getIndividu().getId();
    }

}
