package br.com.renanlabs.sales.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renanlabs.sales.domain.entity.Client;

@Repository
public class Clients {

	
	//do all operations in database
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional
	public Client save(Client client) {
		entityManager.persist(client);
		return client;
	}
	
	
	@Transactional
	public Client update (Client client) {
		entityManager.merge(client);
		return client;
	}
	
	@Transactional
	public void delete(Client client) {
		if(!entityManager.contains(client)) {
			client = entityManager.merge(client);
		}
		entityManager.remove(client);
	}
	
	@Transactional
	public void delete(Integer id) {
		Client client = entityManager.find(Client.class, id);
		delete(client);
	}
	
	
	@Transactional(readOnly = true)
	public List<Client> findByName(String name){
		String jpql = "select c from Client c where c.name = :name";
		TypedQuery<Client> query =  entityManager.createQuery(jpql, Client.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public List<Client> findAll(){	
		return entityManager.createQuery("from Client", Client.class).getResultList();
	}
	
	
}
