package com.orangetalents.simpleApiToBank.config;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.orangetalents.simpleApiToBank.entities.Account;
import com.orangetalents.simpleApiToBank.entities.Client;
import com.orangetalents.simpleApiToBank.repositories.AccountRepository;
import com.orangetalents.simpleApiToBank.repositories.ClientRepository;

@Configuration
@Profile("data")
public class DataConfiguration implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Client c1 = new Client(null, "Maria Brown", "maria@gmail.com", "988.97.526/88", "1996-10-23T19:33:30Z");
		Client c2 = new Client(null, "Alex Green", "alex@gmail.com", "977777777", "1991-12-23T19:53:07Z");
		Client c3 = new Client(null, "Green", "alex@gmail.com", "977777777", "1991-12-23T19:53:07Z");
		
		Account a1 = new Account(null, "Conta Poupanca",c1);
		Account a2 = new Account(null, "Conta Corrente",c2);
		Account a3 = new Account(null, "Conta Corrente",c3);
		Account a4 = new Account(null, "Conta Poupanca",c1);
		Account a5 = new Account(null, "Conta Corrente",c2);
		Account a6 = new Account(null, "Conta Corrente",c3);
		
		clientRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		accountRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6));
	}
	
}
