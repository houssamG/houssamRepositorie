package com.houssam.gestiondestock.config;


import com.houssam.gestiondestock.model.Utilisateur;
import com.houssam.gestiondestock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class SecurityConfig implements UserDetailsService{

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
// toutes les requetes ont besoin d'etre autentifié
        httpSecurity.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/v3/api-docs").permitAll().
                requestMatchers("/gestiondestock/v1/**").authenticated());
// demander a spring d'afficher un formulaire d'athentification pour toutes les requetes
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.withDefaultPasswordEncoder().username("houssam").password("houssam").build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserDetails userDetails = User.withDefaultPasswordEncoder().username("houssam").password("houssam").build();
        Utilisateur utilisateur = utilisateurRepository.findByMail(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role-> authorities.add(new SimpleGrantedAuthority(role.getNom())));
        return new User(utilisateur.getMail(),utilisateur.getMotDepasse(),authorities);
    }
}
