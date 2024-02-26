package com.heliant.springproject.bezbednost;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class BezbednosneKonfiguracije{

    private final JwtAutentikacioniFilter jwtAutentikacioniFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("https://HELIANT_DOMAIN/jwks.json").build();
    }

    @Bean
    public PasswordEncoder enkoderLozinke() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/formulari").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/formulari").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/formulari").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/formularipopunjeni").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/formularipopunjeni").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/formularipopunjeni").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/polja").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/polja").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/polja").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/popunjenapolja").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/popunjenapolja").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/popunjenapolja").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));

        http.addFilterBefore(jwtAutentikacioniFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
