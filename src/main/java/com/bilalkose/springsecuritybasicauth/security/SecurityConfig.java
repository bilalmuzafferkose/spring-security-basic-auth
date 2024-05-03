package com.bilalkose.springsecuritybasicauth.security;

import com.bilalkose.springsecuritybasicauth.model.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security, HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvcRequestBuilder = new MvcRequestMatcher.Builder(introspector); //h2-console için kullanmamız gereken bir konfigürasyon. Tüm patternleri mvcRequestBuilder pattern a göre düzenleyeceğiz

        security
                .headers( x->x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(csrfConfig -> csrfConfig.ignoringRequestMatchers(mvcRequestBuilder.pattern("/public/**"))
                        .ignoringRequestMatchers(PathRequest.toH2Console()))
                //farklı farklı authorize methodları yazmak yerine bir tane lambda fonksiyonu kullanılabilir.
                //özelden genele gidildiği unutulmamalı
                .authorizeHttpRequests(x ->
                                x
                                        .requestMatchers(mvcRequestBuilder.pattern("/public/**")).permitAll()
                                        .requestMatchers(mvcRequestBuilder.pattern("/private/admin/**")).hasRole(Role.ROLE_ADMIN.getValue())
                                        .requestMatchers(mvcRequestBuilder.pattern("/private/**")).hasAnyRole(Role.ROLE_ADMIN.getValue(),Role.ROLE_BILAL.getValue())
                                        .requestMatchers(PathRequest.toH2Console()).hasRole("ADMIN")
                                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(x-> x.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)); //session time-out

        return security.build();
    }
}
