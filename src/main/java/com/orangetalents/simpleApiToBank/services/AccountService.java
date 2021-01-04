package com.orangetalents.simpleApiToBank.services;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.orangetalents.simpleApiToBank.entities.Account;
import com.orangetalents.simpleApiToBank.repositories.AccountRepository;
import com.orangetalents.simpleApiToBank.services.exceptions.DatabaseException;
import com.orangetalents.simpleApiToBank.services.exceptions.ResourceNotFoundException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Account findById(Long id) {
		Optional<Account> accountId = accountRepository.findById(id);
		return accountId.orElseThrow(() -> new ResourceNotFoundException(id)); //Criando tratamento de exceção 404
	}

	public Account insert(Account account) { // Criando método com nome insert para salvar no bando de dados. Ele será
											// usado na AccountResource para o mapeamento POST.
		return accountRepository.save(account);
	}

	public void delete(Long id) {	//indicação de void pois não irá retornar nada
		try {
			accountRepository.deleteById(id);
			} catch (EmptyResultDataAccessException rnfe) {
				throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException rnfe) {
				throw new DatabaseException(rnfe.getMessage());
			}
		}
	
}
