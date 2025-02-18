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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(authorize -> authorize
    .requestMatchers(antMatcher("/login")).permitAll() //Salli login
    .requestMatchers(antMatcher("/register")).permitAll() //Salli login
    .requestMatchers(antMatcher("/css/**")).permitAll() //Salli css
    .requestMatchers(antMatcher("/images/**")).permitAll() //Salli kuvat
    .requestMatchers(antMatcher("/")).permitAll() //Salli indeksi
    .requestMatchers(antMatcher("/reviews/**")).permitAll() //Salli kaikille arvostelut
    .requestMatchers(antMatcher("/database/**")).permitAll() //salli h2-console
    .requestMatchers(antMatcher("/api/**")).permitAll() //Salli api
    .requestMatchers(antMatcher("/error")).permitAll() //SALLI ERRORIT %-))
    .anyRequest().authenticated()
    )
      .formLogin(formlogin -> formlogin
        .loginPage("/login")
        .defaultSuccessUrl("/") //TODO: Tee käytettävyyden kannalta parempi logiikka
        .permitAll()
      )
      //TODO: Poista postgresql jälkeen? Mahdollistaa h2-consolen
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

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/images/**")
        .allowedOrigins(
          "https://cdn2.steamgriddb.com",
          "https://cdn.cloudflare.steamstatic.com",
          "https://web.postman.co"
        )
        .allowedHeaders("Content-Type")
        .allowedMethods("GET", "POST", "PUT")
        .allowCredentials(false);
      }
    };
  }
}