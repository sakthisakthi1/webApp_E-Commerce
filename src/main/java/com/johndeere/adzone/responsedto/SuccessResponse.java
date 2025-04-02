package com.johndeere.adzone.responsedto;

public class SuccessResponse {

	private String messageCode;
	private String messageDescription;
	
	public SuccessResponse() {
	}

	public SuccessResponse(String messageCode, String messageDescription) {
		super();
		this.messageCode = messageCode;
		this.messageDescription = messageDescription;
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * @return the messageDescription
	 */
	public String getMessageDescription() {
		return messageDescription;
	}

	/**
	 * @param messageDescription the messageDescription to set
	 */
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	
}
