package org.formation.service;

import java.util.HashSet;
import java.util.Set;

import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			
		Member m = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No such " + username));
		
		if ( m.getEmail().contains("dthibau") ) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return new User(m.getEmail(), "{noop}" + m.getPassword(), grantedAuthorities);
	}

}
