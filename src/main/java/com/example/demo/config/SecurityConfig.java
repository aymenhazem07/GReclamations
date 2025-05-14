package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll() // Autoriser l'accès aux ressources statiques sans authentification
                .antMatchers("/login", "/register").permitAll() // Autoriser l'accès à la page de connexion et d'inscription sans authentification
                .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                .and()
            .formLogin()
                .loginPage("/login") // Configurer la page de connexion personnalisée
                .defaultSuccessUrl("/index", true) // Rediriger vers le tableau de bord après une connexion réussie
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout") // Configurer l'URL de déconnexion personnalisée
                .logoutSuccessUrl("/login?logout") // Rediriger vers la page de connexion après une déconnexion réussie
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // Configuration d'une authentification en mémoire pour les tests
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
