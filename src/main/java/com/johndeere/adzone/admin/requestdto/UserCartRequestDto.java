package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class UserCartRequestDto {


    private BigInteger userCartId;

    private BigInteger documentId;

    private  String createdBy;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String updatedBy;

    private Integer deleteFlag;

    private BigInteger documentFileId;

    private Integer productFlag;

    public UserCartRequestDto(BigInteger userCartId, BigInteger documentId, String createdBy, LocalDateTime createdDate,
            LocalDateTime updatedDate, String updatedBy, Integer deleteFlag, BigInteger documentFileId,
            Integer productFlag) {
        this.userCartId = userCartId;
        this.documentId = documentId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
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

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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
        return "UserCartRequestDto [createdBy=" + createdBy + ", createdDate=" + createdDate + ", deleteFlag="
                + deleteFlag + ", documentFileId=" + documentFileId + ", documentId=" + documentId + ", productFlag="
                + productFlag + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", userCartId="
                + userCartId + "]";
    }


   
   
}
