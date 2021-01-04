package com.orangetalents.simpleApiToBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orangetalents.simpleApiToBank.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	


}
