package getionRh.example.rh.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfigurer {

    /**
     * Methode qui crypte le password
     * 
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Methode pour filter
     * 
     * @return
     */
    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    @Bean
    public SecurityFilterChain configurer(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET, "/etat-candidature", "/candidature")
                        .hasAnyAuthority("CANDIDAT")
                        .requestMatchers(HttpMethod.POST, "/api/login", "/api/register",
                                "/testtest/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/etat-candidature",
                                "/nom-document", "/candidature")
                        .hasAnyAuthority("ADMIN") // pour
                        .requestMatchers(HttpMethod.PUT, "/etat-candidature", "/poste-de-travail",
                                "/session-candidature", "/nom-document")
                        .hasAnyAuthority("ADMIN")
                        .anyRequest().permitAll())

                .addFilterAfter(securityFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
