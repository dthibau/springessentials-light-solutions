package org.formation;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		    .antMatchers("/home","/","/swagger-ui/**","/v3/api-docs","/actuator/**").permitAll()
		    .antMatchers(HttpMethod.GET,"/members/**").authenticated()
		    .antMatchers("/members/**").hasRole("ADMIN")
			.and()
			.formLogin();
//			.httpBasic();
//			.oauth2Login();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**/*.css","/**/*.js");
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication().withUser("user").password("{noop}secret").roles("USER")
//		.and()
//		.withUser("admin").password("{noop}secret").roles("ADMIN");
//	}

	
}
