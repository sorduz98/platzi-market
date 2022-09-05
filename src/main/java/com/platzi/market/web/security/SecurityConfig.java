package com.platzi.market.web.security;

import com.platzi.market.domain.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> swaggerRoutes = Arrays.asList(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        );

        List<String> unauthorizedRoutes = Arrays.asList(
                "/auth/**"
        );

        /** Set users in userDetailsService */
        http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userSecurityService);

        http.csrf().disable()
                .authorizeRequests().antMatchers(
                        Stream.concat(
                                    swaggerRoutes.stream(),
                                    unauthorizedRoutes.stream()
                                )
                                .toArray(String[]::new)
                ).permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /** Error handling */
        http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"data\": null,\"error\": \"Upps!, An error has ocurred!\"}");
        });

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
