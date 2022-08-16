package br.com.renanlabs.sales.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.renanlabs.sales.domain.entity.Client;
import br.com.renanlabs.sales.domain.entity.Order;

public interface Orders extends JpaRepository<Order, Integer>{

	List<Order> findByClient(Client client);
	
	
	@Query("select o from Order o left join fetch o.itens where o.id = :id ")
	Optional<Order> findByIdFetchItens(@Param("id") Integer id);
	
}
