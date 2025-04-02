package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;

public class ProductResponseDto {
    
    private BigInteger documentId;

	private String documentName;
    
    private BigInteger documentSubCategoryId;

    private String documentSubCategoryName;

    private BigInteger documentCategoryId;

    private String documentCategoryName;

    private BigInteger documentSubChildCategoryId;

    private String documentSubChildCategoryName;

    private BigInteger thumbnailId;

    private String thumbnailName;

    private String thumbnailUrl;

    private String currency;

    private float price;
    
    private String specification;

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

    public BigInteger getDocumentSubCategoryId() {
        return documentSubCategoryId;
    }

    public void setDocumentSubCategoryId(BigInteger documentSubCategoryId) {
        this.documentSubCategoryId = documentSubCategoryId;
    }

    public String getDocumentSubCategoryName() {
        return documentSubCategoryName;
    }

    public void setDocumentSubCategoryName(String documentSubCategoryName) {
        this.documentSubCategoryName = documentSubCategoryName;
    }

    public BigInteger getDocumentCategoryId() {
        return documentCategoryId;
    }

    public void setDocumentCategoryId(BigInteger documentCategoryId) {
        this.documentCategoryId = documentCategoryId;
    }

    public String getDocumentCategoryName() {
        return documentCategoryName;
    }

    public void setDocumentCategoryName(String documentCategoryName) {
        this.documentCategoryName = documentCategoryName;
    }

    public BigInteger getDocumentSubChildCategoryId() {
        return documentSubChildCategoryId;
    }

    public void setDocumentSubChildCategoryId(BigInteger documentSubChildCategoryId) {
        this.documentSubChildCategoryId = documentSubChildCategoryId;
    }

    public String getDocumentSubChildCategoryName() {
        return documentSubChildCategoryName;
    }

    public void setDocumentSubChildCategoryName(String documentSubChildCategoryName) {
        this.documentSubChildCategoryName = documentSubChildCategoryName;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
    
}
