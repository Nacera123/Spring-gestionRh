package getionRh.example.rh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.entity.Pays;
import lombok.Data;


@Data
public class PaysDto {

    public PaysDto(){

    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("abreviation")
    private String abreviation;


    public PaysDto(Pays pays){
        if (pays != null) {
        id = pays.getId();
        designation = pays.getDesignation();
        abreviation = pays.getAbreviation();

        }
    }



}
