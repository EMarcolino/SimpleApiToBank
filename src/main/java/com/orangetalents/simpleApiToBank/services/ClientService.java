package com.orangetalents.simpleApiToBank.services;

import java.util.List;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.orangetalents.simpleApiToBank.entities.Client;
import com.orangetalents.simpleApiToBank.repositories.ClientRepository;
import com.orangetalents.simpleApiToBank.services.exceptions.DatabaseException;
import com.orangetalents.simpleApiToBank.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> findAll() {		
		return clientRepository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> clientId = clientRepository.findById(id);
		return clientId.orElseThrow(() -> new ResourceNotFoundException(id)); //Criando tratamento de exceção 404

	}
	
	public Client insert(Client client) {	//Criando método com nome insert para salvar no bando de dados. Ele será 
											//usado na ClientResourcepara o mapeamento POST.  
		return	clientRepository.save(client);
	}
	
	public Client update(Long id, Client client) {
		try {
			Client  monClient =  clientRepository.getOne(id);
			updateData(monClient,client);
			return clientRepository.save(monClient);
		} catch (EntityNotFoundException rnfe) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Client monClient, Client client) {
		monClient.setName(client.getName());
		monClient.setEmail(client.getEmail());
		monClient.setCpf(client.getCpf());
		monClient.setDateBirth(client.getDateBirth());
	}

	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
			} catch (EmptyResultDataAccessException rnfe) {
				throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException rnfe) {
				throw new DatabaseException(rnfe.getMessage());
			}
		}
	
}
