package ru.sccs.playground1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.sccs.playground1.service.impl.SystemUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final ApplicationContext applicationContext;
//    private final JwtTokenProvider jwtTokenProvider;

//    private final SystemUserDetailsServiceImpl systemUserDetailsService;

    private final JWTFilter jwtFilter;
//    private final PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("api/v1/user/**").hasAuthority("USER"))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("api/v1/user/**").hasAuthority("USER"))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/**", "/swagger-ui/**").permitAll())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
//                        .authenticationEntryPoint(((request, response, authException) -> {
//                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                            response.getWriter().write("Unauthorized.");
//                        }))
//                        .accessDeniedHandler(((request, response, accessDeniedException) -> {
//                            response.setStatus(HttpStatus.FORBIDDEN.value());
//                            response.getWriter().write("Forbidden.");
//                        })))
//                .anonymous(AbstractHttpConfigurer::disable)

//                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//                .csrf().disable()
//                .cors()
//                .and()
//                .httpBasic().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(((request, response, authException) -> {
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.getWriter().write("Unauthorized.");
//                }))
//                .accessDeniedHandler(((request, response, accessDeniedException) -> {
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    response.getWriter().write("Forbidden.");
//                }))
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth").permitAll()
//                .anyRequest().authenticated()
//                .and().anonymous().disable()
//                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
