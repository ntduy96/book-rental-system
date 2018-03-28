package com.chothuesach.exception;

public class BookTitleExistsException extends RuntimeException {
	
	static final long serialVersionUID = 1L;
	
	private String message = "Tên sách này đã tồn tại!";

	private String tenSach;

	/**
	 * 
	 */
	public BookTitleExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tenSach
	 */
	public BookTitleExistsException(String tenSach) {
		super();
		this.tenSach = tenSach;
		this.message = "Tên sách " + this.tenSach + " đã tồn tại!";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

}
