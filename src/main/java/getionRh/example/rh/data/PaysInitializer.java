package getionRh.example.rh.data;


import getionRh.example.rh.entity.Pays;
import getionRh.example.rh.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
//public class SituationFamilialeInitializer  {
public class PaysInitializer implements ApplicationRunner {


    @Autowired
    private PaysService paysService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Initializing data...");


        if (this.paysService.counPays() == 0) {



            String[] pays  = {"Marie","Célibataire", "Pacsé"};
            if (this.paysService.counPays() == 0) {
                String[][] paysData = {
                        {"AFGHANISTAN", "093"},
                        {"AFRIQUE DU SUD", "027"},
                        {"ALBANIE", "355"},
                        {"ALGERIE", "213"},
                        {"ALLEMAGNE", "049"},
                        {"ANGLETERRE", "044"},
                        {"ANDORRE", "376"},
                        {"ANGOLA", "244"},
                        {"ANGUILLA", "810"},
                        {"ANTIGUA-ET-BARBUDA", "811"},
                        {"ARABIE SAOUDITE", "966"},
                        {"ARGENTINE", "054"},
                        {"ARMENIE", "374"},
                        {"AUSTRALIE", "061"},
                        {"AUTRICHE", "043"},
                        {"AZERBAÏDJAN", "994"},
                        {"BAHAMAS", "812"},
                        {"BAHREÏN", "973"},
                        {"BANGLADESH", "880"},
                        {"BARBADE", "246"},
                        {"BELGIQUE", "032"},
                        {"BELIZE", "501"},
                        {"BENIN", "229"},
                        {"BHOUTAN", "975"},
                        {"BIELORUSSIE", "375"},
                        {"BIRMANIE", "095"},
                        {"BOLIVIE", "591"},
                        {"BOSNIE-HERZEGOVINE", "387"},
                        {"BOTSWANA", "267"},
                        {"BRESIL", "055"},
                        {"BRUNEI DARUSSALAM", "673"},
                        {"BULGARIE", "359"},
                        {"BURKINA-FASO", "226"},
                        {"BURUNDI", "257"},
                        {"CAMBODGE", "855"},
                        {"CAMEROUN", "237"},
                        {"CANADA", "010"},
                        {"CAP-VERT", "238"},
                        {"CENTRAFRICAINE (République)", "236"},
                        {"CHILI", "056"},
                        {"CHINE", "086"},
                        {"CHYPRE", "357"},
                        {"COLOMBIE", "057"},
                        {"COMORES", "269"},
                        {"CONGO", "242"},
                        {"CONGO (Rép. Démocratique)", "243"},
                        {"CORÉE-DU-NORD (Rép. Populaire Démocratique)", "850"},
                        {"CORÉE-DU-SUD (Rép. Populaire Démocratique)", "082"},
                        {"COSTA RICA", "506"},
                        {"COTE D'IVOIRE", "225"},
                        {"CROATIE", "385"},
                        {"CUBA", "053"},
                        {"DANEMARK", "045"},
                        {"DJIBOUTI", "253"},
                        {"DOMINICAINE (République)", "809"},
                        {"DOMINIQUE", "813"},
                        {"EGYPTE", "020"},
                        {"EL SALVADOR", "503"},
                        {"EMIRATS ARABES UNIS", "971"},
                        {"EQUATEUR", "593"},
                        {"ERYTHREE", "291"},
                        {"ESPAGNE", "034"},
                        {"ESTONIE", "372"},
                        {"ETATS-UNIS", "001"},
                        {"ETHIOPIE", "251"},
                        {"FIDJI", "679"},
                        {"FINLANDE", "358"},
                        {"FRANCE", "033"},
                        {"GABON", "241"},
                        {"GAMBIE", "220"},
                        {"GEORGIE", "995"},
                        {"GHANA", "233"},
                        {"GIBRALTAR", "350"},
                        {"GRECE", "030"},
                        {"GRENADE", "814"},
                        {"GUATEMALA", "502"},
                        {"GUINEE", "224"},
                        {"GUINEE EQUATORIALE", "240"},
                        {"GUINEE-BISSAO", "245"},
                        {"GUYANA", "592"},
                        {"HAÏTI", "509"},
                        {"HONDURAS", "504"},
                        {"HONGRIE", "036"},
                        {"INDE", "091"},
                        {"INDONESIE", "062"},
                        {"IRAK", "964"},
                        {"IRAN", "098"},
                        {"IRLANDE", "353"},
                        {"ISLANDE", "354"},
                        {"ITALIE", "039"},
                        {"JAMAÏQUE", "817"},
                        {"JAPON", "081"},
                        {"JORDANIE", "962"},
                        {"KAZAKHSTAN", "073"},
                        {"KENYA", "254"},
                        {"KIRGHIZISTAN", "996"},
                        {"KIRIBATI", "686"},
                        {"KOSOVO", "999"},
                        {"KOWEÏT", "965"},
                        {"LAOS", "856"},
                        {"LESOTHO", "266"},
                        {"LETTONIE", "371"},
                        {"LIBAN", "961"},
                        {"LIBERIA", "231"},
                        {"LIBYE", "218"},
                        {"LIECHTENSTEIN", "410"},
                        {"LITUANIE", "370"},
                        {"LUXEMBOURG", "352"},
                        {"MACEDOINE", "389"},
                        {"MADAGASCAR", "261"},
                        {"MALAISIE", "060"},
                        {"MALAWI", "265"},
                        {"MALDIVES (Iles)", "960"},
                        {"MALI", "223"},
                        {"MALTE", "356"},
                        {"MAROC", "212"},
                        {"MARSHALL (Iles)", "692"},
                        {"MAURICE", "230"},
                        {"MAURITANIE", "222"},
                        {"MEXIQUE", "052"},
                        {"MICRONESIE", "691"},
                        {"MOLDAVIE", "037"},
                        {"MONACO", "377"},
                        {"MONGOLIE", "976"},
                        {"MONTENEGRO", "382"},
                        {"MOZAMBIQUE", "258"},
                        {"NAMIBIE", "264"},
                        {"NAURU", "674"},
                        {"NEPAL", "977"},
                        {"NICARAGUA", "505"},
                        {"NIGER", "227"},
                        {"NIGERIA", "234"},
                        {"NORVEGE", "047"},
                        {"NOUVELLE CALEDONIE", "687"},
                        {"NOUVELLE-ZELANDE", "064"},
                        {"OMAN", "968"},
                        {"OUGANDA", "256"},
                        {"OUZBEKISTAN", "071"},
                        {"PAKISTAN", "092"},
                        {"PALAOS", "680"},
                        {"PANAMA", "507"},
                        {"PAPOUASIE-NOUVELLE-GUINEE", "675"},
                        {"PARAGUAY", "595"},
                        {"PAYS-BAS", "031"},
                        {"PEROU", "051"},
                        {"PHILIPPINES", "063"},
                        {"POLOGNE", "048"},
                        {"PORTUGAL", "351"},
                        {"QATAR", "974"},
                        {"ROUMANIE", "040"},
                        {"RUSSIE", "007"},
                        {"RWANDA", "250"},
                        {"SAINTE-LUCIE", "758"},
                        {"SAINT-MARIN", "378"},
                        {"SAINT-VINCENT-ET-LES-GRENADINES", "818"},
                        {"SALOMON (Iles)", "677"},
                        {"SAMOA OCCIDENTALES", "685"},
                        {"SAO TOME ET PRINCIPE", "239"},
                        {"SENEGAL", "221"},
                        {"SERBIE", "038"},
                        {"SEYCHELLES", "248"},
                        {"SIERRA LEONE", "232"},
                        {"SINGAPOUR", "065"},
                        {"SLOVAQUIE", "042"},
                        {"SLOVENIE", "386"},
                        {"SOMALIE", "252"},
                        {"SOUDAN", "249"},
                        {"SOUDAN DU SUD", "211"},
                        {"SRI LANKA", "094"},
                        {"SUEDE", "046"},
                        {"SUISSE", "041"},
                        {"SURINAME", "597"},
                        {"SWAZILAND", "268"},
                        {"SYRIE", "963"},
                        {"TADJIKISTAN", "072"},
                        {"TAIWAN", "886"},
                        {"TANZANIE", "255"},
                        {"TCHAD", "235"},
                        {"TCHEQUE (République)", "420"},
                        {"TERRITOIRES PALESTINIENS", "970"},
                        {"THAÏLANDE", "066"},
                        {"TIMOR ORIENTAL", "670"},
                        {"TOGO", "228"},
                        {"TONGA (Iles)", "676"},
                        {"TRINITE-ET-TOBAGO", "868"},
                        {"TUNISIE", "216"},
                        {"TURKMENISTAN", "993"},
                        {"TUVALU", "688"},
                        {"UKRAINE", "380"},
                        {"URUGUAY", "598"},
                        {"VANUATU", "678"},
                        {"VENEZUELA", "058"},
                        {"VIETNAM", "084"},
                        {"YEMEN", "967"},
                        {"ZAMBIE", "260"},
                        {"ZIMBABWE", "263"}
                };








                for (String[] paysInfo : paysData) {
                    Pays pays1 = new Pays();
                    pays1.setDesignation(paysInfo[0]);
                    pays1.setAbreviation(paysInfo[1]);
                    paysService.save(pays1);
                }
            }



        }

        System.out.println("Data initialization complete.");
    }
}
