package com.algaworks.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.crm.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente , Long>{

	

}
