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
		
		http.csrf().disable()
		    .authorizeHttpRequests()
		    .antMatchers("/swagger-ui.html","/swagger-ui/*","/v3/api-docs/**").permitAll()
		    .antMatchers(HttpMethod.GET).authenticated()
		    .antMatchers("/api/**").hasRole("ADMIN")
		    .and()
			.formLogin()
		    .and()
		    .logout().logoutUrl("/logout").logoutSuccessUrl("/api/members");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/**/*.js","/**/*.css","/**/*.png");
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	  
//	  // Ici on utilise un realm mémoire, AuthenticationManagerBuilder permet
//	  // également de facilement de se connecter à un annuaire LDAP ou une bd
//	  auth.inMemoryAuthentication().withUser("user").password("{noop}password").
//	       roles("USER")
//	       .and().withUser("admin").password("{noop}password").
//	       roles("USER", "ADMIN");
//	  }
//	
}
