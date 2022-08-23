package br.com.renanlabs.sales.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanlabs.sales.domain.entity.User;
import br.com.renanlabs.sales.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	
	private final UserServiceImpl userService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody @Valid User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userService.save(user);
	}
}
