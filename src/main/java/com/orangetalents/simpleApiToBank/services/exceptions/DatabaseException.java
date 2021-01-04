package com.orangetalents.simpleApiToBank.services.exceptions;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseException(String messageDataBase) {
		super(messageDataBase);
	}
}
