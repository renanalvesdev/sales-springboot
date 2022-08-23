package br.com.renanlabs.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.renanlabs.sales.domain.entity.User;
import br.com.renanlabs.sales.domain.repository.UserRepository;


@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	
	@Transactional
	public User save(User user) {
		return repository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found in database"));
		
		
		String[] roles = user.isAdmin() ?
					new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(user.getLogin())
				.password(user.getPassword())
				.roles(roles)
				.build();
	}

}
