package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class OrderAddressRequestDto {
    
    private BigInteger addressId;

    private String name;

    private String email;

    private BigInteger contactNumber;

    private String country;

    private String city;

    private String state;

    private String streetAddress;

    private String pincode;

    private String createdBy;

    private LocalDateTime createdDate;

    private String updatedBy;

    private LocalDateTime updatedDate;

    private Integer deleteFlag;

    private BigInteger marketId;

  
    public OrderAddressRequestDto(BigInteger addressId, String name, String email, BigInteger contactNumber,
            String country, String city, String state, String streetAddress, String pincode, String createdBy,
            LocalDateTime createdDate, String updatedBy, LocalDateTime updatedDate,BigInteger marketId, Integer deleteFlag) {
        this.addressId = addressId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.country = country;
        this.city = city;
        this.state = state;
        this.streetAddress = streetAddress;
        this.pincode = pincode;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.deleteFlag = deleteFlag;
       this.marketId = marketId;
    }

    public BigInteger getMarketId() {
        return marketId;
    }

    public void setMarketId(BigInteger marketId) {
        this.marketId = marketId;
    }


    public BigInteger getAddressId() {
        return addressId;
    }

    public void setAddressId(BigInteger addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(BigInteger contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    @Override
    public String toString() {
        return "OrderAddressRequestDto [addressId=" + addressId + ", city=" + city + ", contactNumber=" + contactNumber
                + ", country=" + country + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", deleteFlag="
                + deleteFlag + ", email=" + email + ", name=" + name + ", pincode=" + pincode + ", state=" + state
                + ", streetAddress=" + streetAddress + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
                + ", marketId="+ marketId +"]";
    }

    
    





}
