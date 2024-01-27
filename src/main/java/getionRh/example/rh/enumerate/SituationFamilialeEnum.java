package getionRh.example.rh.enumerate;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SituationFamilialeEnum {
    Celibataire("Celibataire"),
    Marie("Marié"),
    Pacse("Pacsé")
    ;



    private final String designation;



    @JsonCreator
    public static SituationFamilialeEnum getSituationFamilialeEnumByDesignation(String designationValue){

        for (SituationFamilialeEnum situationFamilialeEnum : SituationFamilialeEnum.values() ){
            if (situationFamilialeEnum.getDesignation().equals(designationValue)){
                return situationFamilialeEnum;
            }
        }
        return null;
    }
    SituationFamilialeEnum( String designation) {

        this.designation = designation;
    }



    public String getDesignation() {
        return designation;
    }
}
