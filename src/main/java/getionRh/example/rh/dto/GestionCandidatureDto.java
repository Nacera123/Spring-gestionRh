package getionRh.example.rh.dto;

import getionRh.example.rh.entity.User;
import getionRh.example.rh.entity.candidature.DocumentCandidature;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GestionCandidatureDto {

    //piece jointe
    private String pieceJointe;
    private String typePieceJointe;

    //recuperer les info du candidat
    private String nomIndividu;
    private String prenomIndividu;
    private String telephoneIndividu;
    private String emailIndividu;
    private String mdpIndividu;

    //son pays
    private String paysIndividu;

    //sa civilite
    private String civilite;

    private String nomPoste;



    public GestionCandidatureDto(DocumentCandidature d ){
        User user = new User();
        this.pieceJointe = d.getPieceJointe();
        if(d.getNomPieceJointe()!= null){

            this.typePieceJointe = d.getNomPieceJointe().getNom();
        }
       if (d.getCandidature() != null){
           if (d.getCandidature().getIndividu() != null){
                this.nomIndividu = d.getCandidature().getIndividu().getNom();
                this.prenomIndividu = d.getCandidature().getIndividu().getPrenom();
                this.telephoneIndividu = d.getCandidature().getIndividu().getTelephone();
                this.emailIndividu = d.getCandidature().getIndividu().getEmail();
                this.mdpIndividu = user.getPassword();

                ///pays
               if (d.getCandidature().getIndividu().getPays() != null){
                   this.paysIndividu = d.getCandidature().getIndividu().getPays().getDesignation();
               }

               //civilite

               if (d.getCandidature().getIndividu().getEtatCivilEnum() != null){
                   this.civilite = d.getCandidature().getIndividu().getEtatCivilEnum().getDesignation();
               }


           }

           if (d.getCandidature().getPoste() != null){
               if (d.getCandidature().getPoste().getPoste() != null){
                    this.nomPoste = d.getCandidature().getPoste().getPoste().getNom();
               }
           }
           if(d.getCandidature().getEtatCandidature() != null){
               d.getCandidature().getEtatCandidature().setEtat("Candidature consultée par le recruteur");
           }

       }
    }
}
