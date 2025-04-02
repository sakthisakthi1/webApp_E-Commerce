package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;



public class OrderDto {
	
private BigInteger notificationId;
	
    private String actionId;

    private String ownerId;
    
    private Integer quantity;

    private String productName;
    
    private BigInteger documentFileId;
    
    public BigInteger getDocumentFileId() {
		return documentFileId;
	}

	public void setDocumentFileId(BigInteger documentFileId) {
		this.documentFileId = documentFileId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	private String redirectionId;

    
    private String redirectionPage;

    
    private String message;
    
    
    private String notificationModule;
    
    
    private String keyValue;
    
    
    private boolean isRead;
    
    
    private String createdBy;
    
    
    private LocalDateTime createdDate;
    
    
    private String updatedBy;

    
    private LocalDateTime updatedDate;
    
    
    private BigInteger orderId;
    
    
    private BigInteger orderNumber;
    
    
    private LocalDateTime orderOn;
    
    
    private LocalDateTime deliveredOn;
    
    
    private String racfId;
    
    
    private String orderStatus;
    
    public BigInteger getOrderId() {
		return orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public BigInteger getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(BigInteger orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDateTime getOrderOn() {
		return orderOn;
	}

	public void setOrderOn(LocalDateTime orderOn) {
		this.orderOn = orderOn;
	}

	public LocalDateTime getDeliveredOn() {
		return deliveredOn;
	}

	public void setDeliveredOn(LocalDateTime deliveredOn) {
		this.deliveredOn = deliveredOn;
	}

	public String getRacfId() {
		return racfId;
	}

	public void setRacfId(String racfId) {
		this.racfId = racfId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigInteger getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(BigInteger notificationId) {
		this.notificationId = notificationId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getRedirectionId() {
		return redirectionId;
	}

	public void setRedirectionId(String redirectionId) {
		this.redirectionId = redirectionId;
	}

	public String getRedirectionPage() {
		return redirectionPage;
	}

	public void setRedirectionPage(String redirectionPage) {
		this.redirectionPage = redirectionPage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNotificationModule() {
		return notificationModule;
	}

	public void setNotificationModule(String notificationModule) {
		this.notificationModule = notificationModule;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedDBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}



}
