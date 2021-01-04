package com.orangetalents.simpleApiToBank.entities;

import java.io.Serializable;

//Para importação da ________ para funcionalidade do Hibernate temos duas opções javax.persistence e org.hibernate.annotations. Mas estou dando preferência na utilização do avax.persistence para a aplicação dependa da especificação e não da implementação.
import javax.persistence.Entity;	//Importando a especificação do JPA para as anotações do Hibernate 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity		
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String typeAccount;
	
	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;
	
	public Account() {
		
	}

	public Account(Long id, String typeAccount, Client client) {
		super();
		this.id = id;
		this.typeAccount = typeAccount;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeAccount() {
		return typeAccount;
	}

	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
