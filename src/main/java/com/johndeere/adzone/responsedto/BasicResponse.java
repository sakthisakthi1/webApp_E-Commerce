package com.johndeere.adzone.responsedto;


public class BasicResponse<T> {

	private ErrorResponse error;
	private SuccessResponse message;
	private T data;
	
	public BasicResponse() {
	}

	public BasicResponse(ErrorResponse error, SuccessResponse message, T data) {
		super();
		this.error = error;
		this.message = message;
		this.data = data;
	}

	/**
	 * @return the error
	 */
	public ErrorResponse getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(ErrorResponse error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public SuccessResponse getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(SuccessResponse message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
}