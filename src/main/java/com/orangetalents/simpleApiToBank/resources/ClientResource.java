package com.orangetalents.simpleApiToBank.resources;	//

import java.net.URI;

//importações
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orangetalents.simpleApiToBank.entities.Client;
import com.orangetalents.simpleApiToBank.services.ClientService;


@RestController							//Anotação indicando que a classe é um recurso web implementado por um controlador REST	
@RequestMapping(value = "/clients")		//Anotação indicando o caminho para o esse recurso Ex: http://localhost:8080/clients
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@GetMapping					 
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")					//	/clients/1
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client clientIdObj = service.findById(id);
				
		return ResponseEntity.ok().body(clientIdObj);
	}
	
	@PostMapping
    public ResponseEntity<Client> insert(@Validated @RequestBody Client objClient) {	//Criando método insert com mapeamento do tipo POST, para criar um novo client. A variável toClient é um objeto (intância) de client
		objClient = service.insert(objClient);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objClient.getId()).toUri();
		return ResponseEntity.created(uri).body(objClient);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @Validated @RequestBody Client objClient) {
		objClient = service.update(id, objClient);
		return ResponseEntity.ok().body(objClient);
	}
	
	@DeleteMapping(value = "/{id}")	//no mapeamento DELETE é necessário indicar o caminho
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
