package com.softron.donation.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -5870089777038330318L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable th) {
		super(th);
	}

	public BadRequestException(String message, Throwable th) {
		super(message, th);
	}

}
