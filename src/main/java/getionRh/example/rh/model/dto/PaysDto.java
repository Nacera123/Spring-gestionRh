package getionRh.example.rh.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.model.entity.Individu;
import getionRh.example.rh.model.entity.Pays;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PaysDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("abreviation")
    private String abreviation;

    @JsonProperty("country")
    private List<Integer> country;

    public PaysDto(Pays pays){
        id = pays.getId();
        designation = pays.getDesignation();
        abreviation = pays.getAbreviation();

        List<Integer> countryList = new ArrayList<>();
        if (pays.getCountry() != null && !pays.getCountry().isEmpty()){
            for (Individu ind : pays.getCountry()){
                countryList.add(ind.getId());
            }
        }
        country = countryList;
    }



}
