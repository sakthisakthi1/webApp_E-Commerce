package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;

public class OrderStatusUpdateRequestDto {

    private BigInteger orderId; 

    private String orderStatus;

    public OrderStatusUpdateRequestDto() {
        super();
    }

    public OrderStatusUpdateRequestDto(BigInteger orderId, String orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderStatusUpdateRequestDto [orderId=" + orderId + ", orderStatus=" + orderStatus + "]";
    } 
    
}
