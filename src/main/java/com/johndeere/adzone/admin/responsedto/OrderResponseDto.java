package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class OrderResponseDto {

    private BigInteger orderId;

    private BigInteger orderNumber;
    
    private LocalDateTime orderOn;

    private Integer quantity;

    private LocalDateTime deliveredOn;

    private Integer orderAmount;

    private String racfId;

    private BigInteger documentId;

    private String documentName;

    private String documentFileType;

    private Integer documentFileSize;

    private BigInteger thumbnailId;

    private String thumbnailName;

    private String thumbnailUrl;

    private String orderStatus;

    private Integer deleteFlag;

    private String createdBy;

    private String updateBy;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Integer productFlag;

    private String address;  

    private BigInteger documentFileId;

    private Integer total;

    private String productDescription;

    private BigInteger addressId;


    public BigInteger getAddressId() {
        return addressId;
    }

    public void setAddressId(BigInteger addressId) {
        this.addressId = addressId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFileType() {
        return documentFileType;
    }

    public void setDocumentFileType(String documentFileType) {
        this.documentFileType = documentFileType;
    }

    public Integer getDocumentFileSize() {
        return documentFileSize;
    }

    public void setDocumentFileSize(Integer documentFileSize) {
        this.documentFileSize = documentFileSize;
    }

    public BigInteger getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(BigInteger thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
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

    public Integer getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(Integer productFlag) {
        this.productFlag = productFlag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getDocumentFileId() {
        return documentFileId;
    }

    public void setDocumentFileId(BigInteger documentFileId) {
        this.documentFileId = documentFileId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

   
}

