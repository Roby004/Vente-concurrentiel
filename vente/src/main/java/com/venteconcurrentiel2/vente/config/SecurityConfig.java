package com.venteconcurrentiel2.vente.config;

import com.venteconcurrentiel2.vente.service.ClientService;
import com.venteconcurrentiel2.vente.service.FournisseurService;
import com.venteconcurrentiel2.vente.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FournisseurService fournisseurService;

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement((session) -> session
                            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionFixation((sessionFixation) -> sessionFixation
                        .migrateSession()) // Enable session fixation protection
                            .maximumSessions(1))
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/", "/login-fournisseur", "/signup-fournisseur","/auth/**").permitAll()
                                .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())    //.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .logout(logout -> logout
                        .logoutUrl("fr/logout")
                        .deleteCookies("JSESSIONID")
                );

        //requestMatchers("/dashboard-fournisseur").hasRole("RFOURNISSEUR")
        // Require authentication for all other requests
        return http.build();
    }

    /*@Override //Error:Method does not override method from its superclass
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }*/

    /*
    *   http
                .cors(cors->cors.configurationSource(corsConfigurationSource())) // Disable CSRF
                .authorizeRequests()
                .requestMatchers("/", "/login", "/signup").permitAll()
                // Allow POST requests to the login and sign up endpoints without authentication
                .requestMatchers(HttpMethod.POST, "/auth/client/login", "/auth/fournisseur/login",
                        "/auth/client/signup", "/auth/fournisseur/signup").permitAll()
                // Allow access to login endpoint without authentication
                .requestMatchers("/dashboard-fournisseur").hasRole("RFOURNISSEUR") // Require "FOURNISSEUR" role for dashboard access
                .anyRequest().authenticated();
*/
   @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl(clientService, fournisseurService);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


/*public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("http://localhost:3000");
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
}*/

@Bean
public CorsConfigurationSource corsConfigurationSource() {
    return new CorsConfigurationSource() {
        @Override
        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration ccfg = new CorsConfiguration();
            ccfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
            ccfg.setAllowedMethods(Collections.singletonList("*"));
            ccfg.setAllowCredentials(true);
            ccfg.setAllowedHeaders(Collections.singletonList("*"));
            ccfg.setExposedHeaders(Arrays.asList("Authorization"));
            ccfg.setMaxAge(3600L);
            return ccfg;

        }
    };
}
}