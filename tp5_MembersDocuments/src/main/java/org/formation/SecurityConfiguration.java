package org.formation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**/*.css","/**/*.js");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                .antMatchers("/api/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
        return http.build();
    }

}
