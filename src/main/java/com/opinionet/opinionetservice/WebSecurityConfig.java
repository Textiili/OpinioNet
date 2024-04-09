package com.opinionet.opinionetservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
      http
      .authorizeHttpRequests( authorize -> authorize
        	.requestMatchers(antMatcher("/css/**")).permitAll() //Salli css
          .requestMatchers(antMatcher("/")).permitAll() //Salli indeksi
          .requestMatchers(antMatcher("/reviews/**")).permitAll() //Salli indeksi
          .requestMatchers(antMatcher("/database/**")).permitAll() 
        	.anyRequest().authenticated()
      )
      .formLogin(formlogin -> formlogin
        .defaultSuccessUrl("/", true) // <- käytettävyyden kannalta älä ohjaa aina index?
        .permitAll()
      )
      //To enable h2-console
      .csrf(csrf -> csrf.disable())
      .headers(headers -> headers.disable())

      .logout(logout -> logout
        .invalidateHttpSession(true)
        .logoutSuccessUrl("/")
        .permitAll()
      );
      return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
