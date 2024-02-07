package getionRh.example.rh.manager;

import getionRh.example.rh.service.implementation.UserServiceImpl;

public class TokenGenerater {


    /**
     * Methode qui genere un token
     * ** utilise la methode "generateIntCharCHAR" du RandomGenerater
     * ** lorsque token exist
     * **** verifie l'existance de la token dans methode "isTokenExist" du UserServiceImpl
     *
     * @param userService
     * @return token
     */

    public static String generateToken(UserServiceImpl userService){
        String token;
        do {
            token = RandomGenerater.generateIntCharCHAR(90);

            //ici on verifie si le token genéré n'est pas déja dans la bd => s'il appartient pas deja a  un user
        }while (userService.isTokenExist(token));
        return token;
    }
}
