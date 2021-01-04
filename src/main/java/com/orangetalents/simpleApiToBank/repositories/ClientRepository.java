package com.orangetalents.simpleApiToBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orangetalents.simpleApiToBank.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	

}
