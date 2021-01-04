package com.orangetalents.simpleApiToBank.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Para importação da ________ para funcionalidade do Hibernate temos duas opções javax.persistence e org.hibernate.annotations. Mas estou dando preferência na utilização do avax.persistence para a aplicação dependa da especificação e não da implementação.
import javax.persistence.Entity;	//Importando a especificação do JPA para as anotações do Hibernate 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity		
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String cpf;
	@NotNull
	private String dateBirth;

	@JsonIgnore						
	@OneToMany(mappedBy = "client")
	private List<Account> accounts = new ArrayList<>();
	
	public Client() {
	}

	public Client(Long id, String name, String email, String cpf, String dateBirth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.dateBirth = dateBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
