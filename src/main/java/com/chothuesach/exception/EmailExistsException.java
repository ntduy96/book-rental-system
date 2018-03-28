package com.chothuesach.exception;

public class EmailExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message = "Email này đã tồn tại";

	private String email;

	/**
	 * @param message
	 */
	public EmailExistsException(String email) {
		this.message = "Email " + email + " đã tồn tại";
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
