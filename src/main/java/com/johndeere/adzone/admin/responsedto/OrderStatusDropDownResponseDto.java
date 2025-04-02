package com.johndeere.adzone.admin.responsedto;

import java.math.BigInteger;

public class OrderStatusDropDownResponseDto {
    
    private BigInteger orderStatusDropDownId;

    private String   orderStatusValue;

    public OrderStatusDropDownResponseDto(BigInteger orderStatusDropDownId, String orderStatusValue) {
        this.orderStatusDropDownId = orderStatusDropDownId;
        this.orderStatusValue = orderStatusValue;
    }

    @Override
    public String toString() {
        return "OrderStatusDropDownResponseDto [orderStatusDropDownId=" + orderStatusDropDownId + ", orderStatusValue="
                + orderStatusValue + "]";
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
    
}
