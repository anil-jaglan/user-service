package com.softron.donation.exceptions;

public class NoRecordExistsException extends RuntimeException {

	private static final long serialVersionUID = -6234467539029172020L;

	public NoRecordExistsException(String message) {
		super(message);
	}

}
