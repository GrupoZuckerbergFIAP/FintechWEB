package br.com.fiap.fintech.exception;

public class DBException extends Exception{

	public DBException() {
		
	}
	
	public DBException(String message, Throwable cause, boolean enableSupression, boolean writabletackTrace) {
		super(message, cause, enableSupression, writabletackTrace);
		
	}
	
	public DBException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DBException(String message) {
		super(message);
	}
	
	public DBException(Throwable cause) {
		super(cause);
	}
}
