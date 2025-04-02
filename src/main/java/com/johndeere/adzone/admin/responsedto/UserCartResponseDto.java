package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;
import java.time.LocalDateTime;
 

public class UserCartResponseDto {

    private BigInteger userCartId; 

    private BigInteger documentId;

    private  String createdBy;

    private LocalDateTime createdDate;

    private String updatedBy;

    private LocalDateTime updatedDate;

    private Integer deleteFlag;

    private BigInteger documentFileId;

    private Integer productFlag;

    public UserCartResponseDto(BigInteger userCartId, BigInteger documentId, String createdBy,
            LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate, Integer deleteFlag,
            BigInteger documentFileId, Integer productFlag) {
        this.userCartId = userCartId;
        this.documentId = documentId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.deleteFlag = deleteFlag;
        this.documentFileId = documentFileId;
        this.productFlag = productFlag;
    }

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

    @Override
    public String toString() {
        return "UserCartResponseDto [createdBy=" + createdBy + ", createdDate=" + createdDate + ", deleteFlag="
                + deleteFlag + ", documentFileId=" + documentFileId + ", documentId=" + documentId + ", productFlag="
                + productFlag + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", userCartId="
                + userCartId + "]";
    }


    
    
}
