package com.softron.donation.exceptions;

public class RecordCanNotBeDeletedException extends BadRequestException {

	private static final long serialVersionUID = 3925889431846273421L;

	public RecordCanNotBeDeletedException(String message) {
		super(message);
	}

	public RecordCanNotBeDeletedException(Throwable th) {
		super(th);
	}

	public RecordCanNotBeDeletedException(String message, Throwable th) {
		super(message, th);
	}

}
