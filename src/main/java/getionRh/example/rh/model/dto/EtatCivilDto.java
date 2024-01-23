package getionRh.example.rh.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import getionRh.example.rh.model.entity.EtatCivil;
import getionRh.example.rh.model.entity.Individu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EtatCivilDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("abreviation")
    private String abreviation;

    @JsonProperty("civilites")
    private List<Integer> civilites;

    public EtatCivilDto(EtatCivil etatCivil){
        id = etatCivil.getId();
        designation = etatCivil.getDesignation();
        abreviation = etatCivil.getAbreviation();

        List<Integer> listCivilites = new ArrayList<>();
        if (etatCivil.getCivilites() != null && !etatCivil.getCivilites().isEmpty()){
            for (Individu cv : etatCivil.getCivilites() ){
                    listCivilites.add(cv.getId());
            }
        }
        civilites = listCivilites;
    }

}
