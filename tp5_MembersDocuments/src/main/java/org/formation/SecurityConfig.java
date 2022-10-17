package org.formation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

		return http.csrf().disable()
		    .authorizeRequests()
		    .requestMatchers("/home","/","/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**","/actuator/**").permitAll()
		    .requestMatchers(HttpMethod.GET,"/api/**").authenticated()
		    .requestMatchers("/api/**").hasRole("ADMIN")
		    .anyRequest().authenticated()
			.and()
			.formLogin()
				.and().build();

	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}secret").roles("USER").and().withUser("admin")
				.password("{noop}secret").roles("ADMIN");
	}
}
