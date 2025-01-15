package com.frank.springboot.proy.sistema_empleados.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.frank.springboot.proy.sistema_empleados.security.filter.JwtAuthenticationFilter;
import com.frank.springboot.proy.sistema_empleados.security.filter.JwtAuthorizationFilter;
import com.frank.springboot.proy.sistema_empleados.security.jwt.JwtUtils;



@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurity {
   
    
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    //@Autowired
    //private AuthenticationConfiguration authenticationConfiguration;
    
    @Bean
    PasswordEncoder passwordEncoder(){  
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
   
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        
        return http
            .authorizeHttpRequests(authz-> authz
            .anyRequest().authenticated()
            )
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf-> csrf.disable())
            .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).build();
    }
}
