package kani.springsecurity.Infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity request){
        return request.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(http -> http
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/singup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/home").permitAll()
                        .anyRequest().authenticated()
                        )
                .build();
    }

}
