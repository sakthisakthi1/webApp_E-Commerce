package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

public class MyCartDetailDto {
    
    private BigInteger documentId;

    private String createdBy;

    private BigInteger userCartId;

    private BigInteger documentFileId;

    private String documentFileName;

    private Integer documentFileSize;

    private String documentFileType;

    private String documentS3Url;

    private String thumbnailUrl;

    private String documentName;

    private Date updatedDate;

    private Date createdDate;

    private boolean isWishList;

    private float price ;

    private String  currency;

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

    public BigInteger getUserCartId() {
        return userCartId;
    }

    public void setUserCartId(BigInteger userCartId) {
        this.userCartId = userCartId;
    }

    public BigInteger getDocumentFileId() {
        return documentFileId;
    }

    public void setDocumentFileId(BigInteger documentFileId) {
        this.documentFileId = documentFileId;
    }

    public String getDocumentFileName() {
        return documentFileName;
    }

    public void setDocumentFileName(String documentFileName) {
        this.documentFileName = documentFileName;
    }

    public Integer getDocumentFileSize() {
        return documentFileSize;
    }

    public void setDocumentFileSize(Integer documentFileSize) {
        this.documentFileSize = documentFileSize;
    }

    public String getDocumentFileType() {
        return documentFileType;
    }

    public void setDocumentFileType(String documentFileType) {
        this.documentFileType = documentFileType;
    }

    public String getDocumentS3Url() {
        return documentS3Url;
    }

    public void setDocumentS3Url(String documentS3Url) {
        this.documentS3Url = documentS3Url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isWishList() {
        return isWishList;
    }

    public void setWishList(boolean isWishList) {
        this.isWishList = isWishList;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

   
}
