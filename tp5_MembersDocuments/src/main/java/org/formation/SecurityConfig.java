package org.formation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
<<<<<<< HEAD
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
=======
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> aa6c6c0 (6.3 auth custom + BCrypt)

@Configuration
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

		return http.csrf().disable()
		    .authorizeRequests()
<<<<<<< HEAD
		    .requestMatchers("/home","/","/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**","/actuator/**").permitAll()
		    .requestMatchers(HttpMethod.GET,"/api/**").authenticated()
		    .requestMatchers("/api/**").hasRole("ADMIN")
=======
		    .antMatchers("/api/members/secret","/home","/","/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**","/actuator/**").permitAll()
		    .antMatchers(HttpMethod.GET,"/api/**").authenticated()
		    .antMatchers("/api/**").hasRole("ADMIN")
>>>>>>> aa6c6c0 (6.3 auth custom + BCrypt)
		    .anyRequest().authenticated()
			.and()
			.formLogin()
				.and().build();

	}
	
<<<<<<< HEAD
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}secret").roles("USER").and().withUser("admin")
				.password("{noop}secret").roles("ADMIN");
=======
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
>>>>>>> aa6c6c0 (6.3 auth custom + BCrypt)
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.inMemoryAuthentication().withUser("user").password("{noop}secret").roles("USER").and().withUser("admin")
//				.password("{noop}secret").roles("ADMIN");
//	}
}
