package br.com.renanlabs.sales.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.renanlabs.sales.domain.entity.User;
import br.com.renanlabs.sales.exception.InvalidPasswordException;
import br.com.renanlabs.sales.rest.dto.CredentialsDTO;
import br.com.renanlabs.sales.rest.dto.TokenDTO;
import br.com.renanlabs.sales.security.jwt.JwtService;
import br.com.renanlabs.sales.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	
	private final JwtService jwtService;
	private final UserServiceImpl userService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody @Valid User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userService.save(user);
	}
	
	@PostMapping("/auth")
	public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {
	
		try {
			User user = User.builder()
					.login(credentials.getLogin())
					.password(credentials.getPassword()).build();
			
			UserDetails authenticatedUser = userService.authenticate(user);
			String token = jwtService.generateToken(user);
			
			return new TokenDTO(user.getLogin(), token);
		} catch (UsernameNotFoundException  | InvalidPasswordException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
	
	
}
