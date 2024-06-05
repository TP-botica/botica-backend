package com.pe.botica.configuration;

import com.pe.botica.configuration.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfiguration {
    @Autowired
    private AuthenticationProvider dapAuthProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( sessionMagConfig ->  sessionMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(dapAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authReqConfig -> {
                    authReqConfig.requestMatchers(HttpMethod.POST,"/user/register").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST,"/user/auth").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.GET,"/user/validate").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.GET,"/role/all").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST,"/role/register").permitAll();

                    authReqConfig.anyRequest().authenticated();
                })
                .build();
    }
}
