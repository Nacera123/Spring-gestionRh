package getionRh.example.rh.security;


import getionRh.example.rh.entity.User;
import getionRh.example.rh.manager.JwtTokenGenerater;
import getionRh.example.rh.service.implementation.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UserServiceImpl userService;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        //1-n'execute que les requetes qui vont commencer par "/api/"
        if (request.getRequestURI().startsWith("/api")
                || request.getRequestURI().startsWith("/etat-candidature")
                || request.getRequestURI().startsWith("/poste-de-travail")
                || request.getRequestURI().startsWith("/session-candidature")
                || request.getRequestURI().startsWith("/nom-document")
        ){
            filterChain.doFilter(request,response);
            return;
        }

        // 2- Verifier que le  token existe
        String token = request.getHeader("Authorization");

        //2- a : verifier que le token n'est pas null et contient Bearer au debut
        if (token == null || !token.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        //2- b : si c'est ok lire a partir du septieme caractere "prendre espace"
        try {
            //ici on recuppere la token readToken du JwtTokenGenerater a partir du "Bearer "
            token = JwtTokenGenerater.readToken(token.substring(7));
        }catch (Exception e){
            return;
        }

        // 3- recuperer l'utilisateur grace a son token
        User userConnect = this.userService.findByToken(token);
        if (userConnect == null){
            filterChain.doFilter(request,response);
            return;
        }

        //4- Si le token est valide : connecter l'utilisateur en prenant en considerant son role
        Authentication authentication = new UsernamePasswordAuthenticationToken(userConnect, null, userConnect.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);

    }
}
