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
@Table(name = "user_cart",schema = "\"order\"")
public class UserCart {
    
   @Id
   @Column(name="user_cart_id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private BigInteger userCartId;

   @Column(name="document_id")
   private BigInteger documentId;

   @Column(name="created_by")
   private String createdBy;

   @Column(name="created_date")
   private LocalDateTime createdDate;

   @Column(name="updated_by")
   private String updatedBy;

   @Column(name="updated_date")
    private  LocalDateTime updatedDate;

   @Column(name="delete_flag")
   private Integer deleteFlag;   
   
   @Column(name="document_file_id")
   private BigInteger documentFileId;

   @Column(name="product_flag")
   private Integer productFlag;

public BigInteger getUserCartId() {
    return userCartId;
}

public void setUserCartId(BigInteger userCartId) {
    this.userCartId = userCartId;
}

public BigInteger getDocumentId() {
    return documentId;
}

public void setDocumentId(BigInteger documentId) {
    this.documentId = documentId;
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

public Integer getDeleteFlag() {
    return deleteFlag;
}

public void setDeleteFlag(Integer deleteFlag) {
    this.deleteFlag = deleteFlag;
}

public BigInteger getDocumentFileId() {
    return documentFileId;
}

public void setDocumentFileId(BigInteger documentFileId) {
    this.documentFileId = documentFileId;
}

public Integer getProductFlag() {
    return productFlag;
}

public void setProductFlag(Integer productFlag) {
    this.productFlag = productFlag;
}

  
   
   
}
