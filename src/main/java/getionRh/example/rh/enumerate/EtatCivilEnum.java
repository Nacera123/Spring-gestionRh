package getionRh.example.rh.enumerate;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum EtatCivilEnum {

    Monsieur("Monsieur", "Mr"),
    Madame("Madame", "Mme"),
    Mademoiselle("Mademoiselle", "Mlle");


    private final String designation;
    private final String abreviation;




    @JsonCreator
    public static EtatCivilEnum getEtatCivilEnumFromDesignation(String value) {

        for (EtatCivilEnum etatCivilEnum : EtatCivilEnum.values()) {

            if (etatCivilEnum.getDesignation().equals(value)) {

                return etatCivilEnum;
            }

        }

        return null;
    }
    EtatCivilEnum(String designation, String abreviation){
        this.designation = designation;
        this.abreviation = abreviation;
    }


}
