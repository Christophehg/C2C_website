package c2cwebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.build();
//    }

//    //Création Deux rôles
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password(bCryptPasswordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin") .
//                password(bCryptPasswordEncoder().encode("admin"))
//                .roles("ADMIN") .
//                build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }




    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/test", "/CreationUtilisateur").permitAll() // Permet l'accès sans authentification
                );
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/test", "/CreationUtilisateur").permitAll() // Routes ouvertes
//                        .anyRequest().authenticated() // Authentification requise pour les autres routes
//                )
//                .requiresChannel(channel -> channel
//                        .anyRequest().requiresSecure() // Force HTTPS
//                );
//        return http.build();
//    }



//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService)
//            throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder).and()
//                .build();
//    }


    // Fonction qui définit la configuration CORS
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:3000"); // Autoriser l'origine du frontend
        corsConfig.addAllowedMethod("GET"); // Autoriser la méthode GET
        corsConfig.addAllowedMethod("POST"); // Autoriser la méthode POST
        corsConfig.addAllowedMethod("PUT"); // Autoriser la méthode PUT
        corsConfig.addAllowedMethod("DELETE"); // Autoriser la méthode DELETE
        corsConfig.addAllowedHeader("*"); // Autoriser tous les en-têtes
        corsConfig.setAllowCredentials(true); // Permet les cookies

        // Associer la configuration à un chemin URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Applique la configuration CORS à toutes les routes

        return source;
    }

}
