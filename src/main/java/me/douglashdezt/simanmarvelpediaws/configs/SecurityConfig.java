package me.douglashdezt.simanmarvelpediaws.configs;


import jakarta.servlet.http.HttpServletResponse;
import me.douglashdezt.simanmarvelpediaws.models.User;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import me.douglashdezt.simanmarvelpediaws.utils.CorsFilter;
import me.douglashdezt.simanmarvelpediaws.utils.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JWTFilter filter;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService, JWTFilter filter) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.filter = filter;
    }

    @Bean
    AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder managerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);

        managerBuilder
                .userDetailsService(identifier -> {
                    User user = userService.findUserByEmail(identifier);

                    if (user == null)
                        throw new UsernameNotFoundException("User: " + identifier + ", not found!");

                    return user;
                })
                .passwordEncoder(passwordEncoder);

        return managerBuilder.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Http and cors disabled
        http.httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        //Route filter
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(
                    "/api/auth/login", "/api/auth/register", "/api/health-check"
            ).permitAll()
            .anyRequest().authenticated()
        );

        //Statelessness
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //UnAuthorized handler
        http.exceptionHandling(handling -> handling.authenticationEntryPoint((req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write("{\"message\":\"Unauthorized\", \"data\": null}");
        }));

        //JWT filter
        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
