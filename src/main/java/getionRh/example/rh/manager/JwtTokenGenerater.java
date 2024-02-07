package getionRh.example.rh.manager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Calendar;

public class JwtTokenGenerater {

    /**
     * Ici on crée une secrete key = une cle de cryptage
     * c'est un objet qu'on a remplie nous même
     * @return une key
     */
    private static SecretKey cryptageKey (){
        String secretString = "HiPFWg8i7zD8LgpGM5PCPnbhzZM2t1T0t5T6Ahz05GBzYv1xww";
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
    }


    /**
     * Cette methode va hacher la token en utilisant la hachage de la cle de cryptage qu'on  a crée
     * et genere par la suite un token
     * @param tokenUser c'est le token du user
     * @return
     */
    public static String generateToken(String tokenUser){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,24);

        return Jwts.builder()
                .subject(tokenUser)
                .expiration(calendar.getTime())
                //ici on crée le token user crypté grace a la clé de cryptage
                .signWith(cryptageKey(), Jwts.SIG.HS256)
                .compact();

    }


    /**
     * Methode qui permet de récupérer le tokenUser décrypté
     * @param token
     * @return
     * @throws Exception
     */
    public static String readToken(String token)throws Exception{
        Claims claims = Jwts
                .parser()
                //ici on décrypte on utilisant la meme cle qu'on a utilisé pour crypter
                .verifyWith(cryptageKey())
                .build()
                .parseEncryptedClaims(token)
                .getPayload();
        return claims.getSubject(); // elle renvoie le token user
    }
}
