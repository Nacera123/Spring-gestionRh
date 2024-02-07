package getionRh.example.rh.manager;

import java.util.Random;

public class RandomGenerater {

    /**
     * La fonction qui permet la generation du Random
     * @param chaine
     * @param len
     * @return
     */
    private static String random(String chaine, int len){

        StringBuilder sb = new StringBuilder(len);
        Random random = new Random();

        for (int i = 0; i < len ; i++)
            sb.append(chaine.charAt(random.nextInt(chaine.length())));
        return sb.toString();
    }

    /**
     * Methode qui utilise la methode random et retourne un random 0-9 a-z A-Z
     * @param length
     * @return
     */
    public static String generateIntCharCHAR(int length){
        return random("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_", length);
    }
}
