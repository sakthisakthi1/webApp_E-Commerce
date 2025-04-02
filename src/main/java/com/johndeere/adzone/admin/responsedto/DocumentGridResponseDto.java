package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;
import java.util.Date;

public class DocumentGridResponseDto {

    private BigInteger documentId;
    private String documentName;
    private String documentCode;
    private BigInteger documentCategoryId;
    private String documentCategoryName;
    private BigInteger documentSubCategoryId;
    private String DocumentSubCategoryName;
    private String documentSubChildCategoryName;
    private String documentFileName;
    private String extension;
    private String imgSrc;
    private boolean isWishList;
    private int documentFileSize;
    private BigInteger thumbnailId;
    private String thumbnailName;
    private String thumbnailUrl;
    private Integer productFlag;
    private String currency;
    private Float price;
    private String specification;
    private Date documentCreatedDate;
    private Date documentUpdatedDate;

    private BigInteger documentFileId;
    private BigInteger wishlistId;

    
    public DocumentGridResponseDto() {
    }
    
    public DocumentGridResponseDto(BigInteger documentId, String documentName, String documentCode,
            BigInteger documentCategoryId, String documentCategoryName, BigInteger documentSubCategoryId,
            String documentSubCategoryName, String documentSubChildCategoryName, String documentFileName,
            String extension, String imgSrc, boolean isWishList, int documentFileSize, BigInteger thumbnailId,
            String thumbnailName, String thumbnailUrl, Integer productFlag, String currency, Float price,
            String specification, Date documentCreatedDate, Date documentUpdatedDate, BigInteger documentFileId,
            BigInteger wishlistId) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.documentCode = documentCode;
        this.documentCategoryId = documentCategoryId;
        this.documentCategoryName = documentCategoryName;
        this.documentSubCategoryId = documentSubCategoryId;
        DocumentSubCategoryName = documentSubCategoryName;
        this.documentSubChildCategoryName = documentSubChildCategoryName;
        this.documentFileName = documentFileName;
        this.extension = extension;
        this.imgSrc = imgSrc;
        this.isWishList = isWishList;
        this.documentFileSize = documentFileSize;
        this.thumbnailId = thumbnailId;
        this.thumbnailName = thumbnailName;
        this.thumbnailUrl = thumbnailUrl;
        this.productFlag = productFlag;
        this.currency = currency;
        this.price = price;
        this.specification = specification;
        this.documentCreatedDate = documentCreatedDate;
        this.documentUpdatedDate = documentUpdatedDate;
        this.documentFileId = documentFileId;
        this.wishlistId = wishlistId;
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
    public String getDocumentCode() {
        return documentCode;
    }
    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
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
    public BigInteger getDocumentSubCategoryId() {
        return documentSubCategoryId;
    }
    public void setDocumentSubCategoryId(BigInteger documentSubCategoryId) {
        this.documentSubCategoryId = documentSubCategoryId;
    }
    public String getDocumentSubCategoryName() {
        return DocumentSubCategoryName;
    }
    public void setDocumentSubCategoryName(String documentSubCategoryName) {
        DocumentSubCategoryName = documentSubCategoryName;
    }
    public String getDocumentSubChildCategoryName() {
        return documentSubChildCategoryName;
    }
    public void setDocumentSubChildCategoryName(String documentSubChildCategoryName) {
        this.documentSubChildCategoryName = documentSubChildCategoryName;
    }
    public String getDocumentFileName() {
        return documentFileName;
    }
    public void setDocumentFileName(String documentFileName) {
        this.documentFileName = documentFileName;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public String getImgSrc() {
        return imgSrc;
    }
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
    public boolean isWishList() {
        return isWishList;
    }
    public void setWishList(boolean isWishList) {
        this.isWishList = isWishList;
    }
    public int getDocumentFileSize() {
        return documentFileSize;
    }
    public void setDocumentFileSize(int documentFileSize) {
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
    public Integer getProductFlag() {
        return productFlag;
    }
    public void setProductFlag(Integer productFlag) {
        this.productFlag = productFlag;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) {
        this.specification = specification;
    }
    public Date getDocumentCreatedDate() {
        return documentCreatedDate;
    }
    public void setDocumentCreatedDate(Date documentCreatedDate) {
        this.documentCreatedDate = documentCreatedDate;
    }
    public Date getDocumentUpdatedDate() {
        return documentUpdatedDate;
    }
    public void setDocumentUpdatedDate(Date documentUpdatedDate) {
        this.documentUpdatedDate = documentUpdatedDate;
    }
    public BigInteger getDocumentFileId() {
        return documentFileId;
    }
    public void setDocumentFileId(BigInteger documentFileId) {
        this.documentFileId = documentFileId;
    }
    public BigInteger getWishlistId() {
        return wishlistId;
    }
    public void setWishlistId(BigInteger wishlistId) {
        this.wishlistId = wishlistId;
    }  

    
}
