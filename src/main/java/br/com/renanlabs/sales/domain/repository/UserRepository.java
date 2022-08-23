package br.com.renanlabs.sales.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanlabs.sales.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByLogin(String login);
	
}
