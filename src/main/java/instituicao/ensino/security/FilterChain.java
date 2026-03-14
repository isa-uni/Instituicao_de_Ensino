package instituicao.ensino.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import instituicao.ensino.model.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class FilterChain {

    @Bean
    public org.springframework.security.web.SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityFilter filter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization -> {
                    authorization.requestMatchers(HttpMethod.POST,"/login").permitAll();
                    authorization.requestMatchers(HttpMethod.POST,"/usuarios/cadastrar").permitAll();
                    authorization.requestMatchers(HttpMethod.POST,"/papel/novo").permitAll();
                    authorization.requestMatchers(HttpMethod.DELETE,"/usuarios/deletar/**").permitAll();
                    authorization.requestMatchers("/h2-console/**").permitAll();
                    authorization.requestMatchers(HttpMethod.POST, "/turma/novo").hasRole("PROFESSOR");
                    authorization.requestMatchers(HttpMethod.POST, "/nota/novo").hasRole("PROFESSOR");
                    authorization.anyRequest().authenticated();
                })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService) {
        return new CustomUserDetails(usuarioService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
