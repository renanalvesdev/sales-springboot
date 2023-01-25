package br.com.renanlabs.sales.exception;

public class InvalidPasswordException extends RuntimeException{

	public InvalidPasswordException() {
		super("Incorrect Password");
	}
}
