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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orangetalents.simpleApiToBank.entities.Account;
import com.orangetalents.simpleApiToBank.services.AccountService;

@RestController							//Anotação indicando que a classe é um recurso web implementado por um controlador REST	
@RequestMapping(value = "/accounts")		//Anotação indicando o caminho para o esse recurso Ex: http://localhost:8080/cl 
public class AccountResource {
	
	@Autowired
	private AccountService service;
	
	@GetMapping					//Anotação indicando 
	public ResponseEntity<List<Account>> findAll() {
		List<Account> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")					//	/accounts/1
	public ResponseEntity<Account> findById(@PathVariable Long id) {
		Account accountIdObj = service.findById(id);
				
		return ResponseEntity.ok().body(accountIdObj);
	}
	
	@PostMapping
    public ResponseEntity<Account> insert(@Validated @RequestBody Account objAccount) {	//Implementando métodoo POST (nome: "insert"), para criar uma nova conta.
																						//A variável toAccount é um objeto (intância) de client
		objAccount = service.insert(objAccount);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objAccount.getId()).toUri();
		return ResponseEntity.created(uri).body(objAccount);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
