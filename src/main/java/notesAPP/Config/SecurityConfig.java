package notesAPP.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// --- Capa de seguridad ---

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/login").permitAll() // Permitir acceso a /login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Especificar la página de inicio de sesión
                        .loginProcessingUrl("/perform_login") // Confirma el endpoint de procesamiento
                        .successHandler(successHandler())
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl("/login")
                        .maximumSessions(1)
                        .expiredUrl("/login?sessionExpired=true")
                        .sessionRegistry(sessionRegister())
                ).cors(cors -> cors.disable())
        ;

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegister() {
        return new SessionRegistryImpl();
    }

    public AuthenticationSuccessHandler successHandler()
    {
        return (request, response, authentication) -> {
            response.sendRedirect("/index");
        };
    }
}
