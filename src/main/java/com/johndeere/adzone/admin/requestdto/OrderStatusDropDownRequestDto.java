package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;

public class OrderStatusDropDownRequestDto {
    

    private BigInteger orderStatusDropDownId;

    private String   orderStatusValue;

    public OrderStatusDropDownRequestDto(BigInteger orderStatusDropDownId, String orderStatusValue) {
        this.orderStatusDropDownId = orderStatusDropDownId;
        this.orderStatusValue = orderStatusValue;
    }

    public BigInteger getOrderStatusDropDownId() {
        return orderStatusDropDownId;
    }

    public void setOrderStatusDropDownId(BigInteger orderStatusDropDownId) {
        this.orderStatusDropDownId = orderStatusDropDownId;
    }

    public String getOrderStatusValue() {
        return orderStatusValue;
    }

    public void setOrderStatusValue(String orderStatusValue) {
        this.orderStatusValue = orderStatusValue;
    }

    @Override
    public String toString() {
        return "OrderStatusDropDownRequestDto [orderStatusDropDownId=" + orderStatusDropDownId + ", orderStatusValue="
                + orderStatusValue + "]";
    }

    
}
