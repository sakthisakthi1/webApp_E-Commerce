 package com.johndeere.adzone.admin.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_jd_orders",schema = "\"order\"")
public class Order {
    
    @Id
	@Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger orderId;

    @Column(name ="order_number")
    private BigInteger orderNumber;

    @Column(name="order_on")
    private LocalDateTime orderOn;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="delivered_on")
    private LocalDateTime deliveredOn;

    @Column(name="order_amount")
    private Integer orderAmount;

    @Column(name="racfid")
    private String racfId;

    @Column(name="document_id")
    private BigInteger documentId;

    @Column(name="delete_flag")
    private Integer deleteFlag;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="update_by")
    private String updateBy;

    @Column(name="updated_date")
    private LocalDateTime updateDate;

    @Column(name="product_flag")
    private Integer productFlag;

    @Column(name="address")
    private String address; 

    @Column(name="document_file_id")
    private BigInteger documentFileId;

    @Column(name="address_id")
    private BigInteger addressId;

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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
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

    public BigInteger getAddressId() {
        return addressId;
    }

    public void setAddressId(BigInteger addressId) {
        this.addressId = addressId;
    }

   

     
}
