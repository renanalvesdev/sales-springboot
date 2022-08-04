package br.com.renanlabs.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanlabs.sales.domain.entity.ItemOrder;

public interface ItensOrder extends JpaRepository<ItemOrder, Integer> {

}
