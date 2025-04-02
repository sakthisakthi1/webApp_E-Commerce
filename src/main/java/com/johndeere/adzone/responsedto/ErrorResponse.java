package com.johndeere.adzone.responsedto;

import java.util.List;

public class ErrorResponse {

	public ErrorResponse(String errorCode, String errorMessage) {
		this.erroCode = errorCode;
		this.errorMessage = errorMessage;
	}
	private String erroCode;
	private String errorMessage;
	private List<String> errorDetails;
	
	public ErrorResponse() {
	}

	public ErrorResponse(String erroCode, String errorMessage, List<String> errorDetails) {
		super();
		this.erroCode = erroCode;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}

	/**
	 * @return the erroCode
	 */
	public String getErroCode() {
		return erroCode;
	}

	/**
	 * @param erroCode the erroCode to set
	 */
	public void setErroCode(String erroCode) {
		this.erroCode = erroCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorDetails
	 */
	public List<String> getErrorDetails() {
		return errorDetails;
	}

	/**
	 * @param errorDetails the errorDetails to set
	 */
	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}

}
