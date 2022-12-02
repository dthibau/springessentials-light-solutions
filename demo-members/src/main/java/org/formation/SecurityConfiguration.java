package org.formation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/swagger-ui/**","/v3/api-docs/**","/actuator/**").permitAll()
		    .antMatchers(HttpMethod.GET, "/api/**").authenticated()
		    .antMatchers("/api/**").hasRole("ADMIN")
		    .anyRequest().authenticated()
		    .and()
		    .oauth2Login()
		    .and().csrf().disable();
		
		return http.build();
	}
	

//	    @Bean
//	    public InMemoryUserDetailsManager userDetailsService() {
//	        UserDetails user = User.withUsername("user").password("{noop}secret").roles("USER").build();
//	        UserDetails admin = User.withUsername("admin").password("{noop}secret").roles("ADMIN").build();
//      
//	
//	        return new InMemoryUserDetailsManager(user,admin);
//	    }

}
