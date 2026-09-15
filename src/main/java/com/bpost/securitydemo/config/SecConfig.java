package com.bpost.securitydemo.config;

import com.bpost.securitydemo.entity.EmpEntity;
import com.bpost.securitydemo.filters.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecConfig {

    private final JwtAuthFilter jwtFilter;

    public SecConfig(JwtAuthFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

      return   http.authorizeHttpRequests(auth->auth
                      .requestMatchers("/regEmp", "/error","/login").permitAll()
                      .requestMatchers("/test").hasRole("USER")
                      .anyRequest().authenticated())
              .csrf(csrf->csrf.disable())
              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)


                .build();
    }



    /*@Bean
    public PasswordEncoder createPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }*/

    @Bean
    public UserDetails userDetails(){
        return  new EmpEntity();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetails userDetails,PasswordEncoder passwordEncoder){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

}
