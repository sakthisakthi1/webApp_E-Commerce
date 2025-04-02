package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;
import java.time.LocalDateTime;


public class OrderRequestDto {
    
    

    public OrderRequestDto(BigInteger orderId, BigInteger orderNumber, LocalDateTime orderOn, Integer quantity,
    LocalDateTime deliveredOn, Integer orderAmount, String racfId, BigInteger documentId, String orderStatus,
            Integer deleteFlag, String createdBy, String updateBy, LocalDateTime createdDate, String productFlag,
            LocalDateTime updatedDate, BigInteger documentFileId, BigInteger addressId) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderOn = orderOn;
        this.quantity = quantity;
        this.deliveredOn = deliveredOn;
        this.orderAmount = orderAmount;
        this.racfId = racfId;
        this.documentId = documentId;
        this.orderStatus = orderStatus;
        this.deleteFlag = deleteFlag;
        this.createdBy = createdBy;
        this.updateBy = updateBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.productFlag = productFlag;
        this.documentFileId = documentFileId;
        this.addressId= addressId;
    }

    private BigInteger orderId;

    private BigInteger orderNumber;

    private LocalDateTime orderOn;

    private Integer quantity;

    private LocalDateTime deliveredOn;

    private Integer orderAmount;

    private String  racfId;

    private BigInteger documentId;

    private String orderStatus;

    private Integer deleteFlag;

    private String createdBy;

    private String updateBy;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String productFlag;

    private BigInteger documentFileId ;

    private BigInteger addressId;
    


    
  

    @Override
    public String toString() {
        return "OrderRequestDto [createdBy=" + createdBy + ", createdDate=" + createdDate + ", deleteFlag=" + deleteFlag
                + ", deliveredOn=" + deliveredOn + ", documentId=" + documentId + ", orderAmount=" + orderAmount
                + ", orderId=" + orderId + ", orderNumber=" + orderNumber + ", orderOn=" + orderOn + ", orderStatus="
                + orderStatus + ", quantity=" + quantity + ", racfId=" + racfId + ", updateBy=" + updateBy
                + ", updatedDate=" + updatedDate + ", addressId="+ addressId +",productFlag="+ productFlag +" , documentFileId="+ documentFileId +"]";
    }

    public BigInteger getAddressId() {
        return addressId;
    }

    public void setAddressId(BigInteger addressId) {
        this.addressId = addressId;
    }

    public BigInteger getDocumentFileId() {
        return documentFileId;
    }

    public void setDocumentFileId(BigInteger documentFileId) {
        this.documentFileId = documentFileId;
    }

    public String getProductFlag() {
        return productFlag;
    }
    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDeliveredOn() {
        return deliveredOn;
    }

    public void setDeliveredOn(LocalDateTime deliveredOn) {
        this.deliveredOn = deliveredOn;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getRacfId() {
        return racfId;
    }

    public void setRacfId(String racfId) {
        this.racfId = racfId;
    }

    public BigInteger getDocumentId() {
        return documentId;
    }

    public void setDocumentId(BigInteger documentId) {
        this.documentId = documentId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    
    
   

    

    
    
 
}
