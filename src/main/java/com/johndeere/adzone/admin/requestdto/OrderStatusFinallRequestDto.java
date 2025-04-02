package com.johndeere.adzone.admin.requestdto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class OrderStatusFinallRequestDto {

    private List<OrderStatusUpdateRequestDto> id ;

    private String orderStatus;

    private Integer isSelectAll;

    private BigInteger[] unSelectIds;

    public OrderStatusFinallRequestDto() {
        super();
    }

    public OrderStatusFinallRequestDto(List<OrderStatusUpdateRequestDto> id, String orderStatus, Integer isSelectAll,
            BigInteger[] unSelectIds) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.isSelectAll = isSelectAll;
        this.unSelectIds = unSelectIds;
    }

    public List<OrderStatusUpdateRequestDto> getId() {
        return id;
    }

    public void setId(List<OrderStatusUpdateRequestDto> id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsSelectAll() {
        return isSelectAll;
    }

    public void setIsSelectAll(Integer isSelectAll) {
        this.isSelectAll = isSelectAll;
    }

    public BigInteger[] getUnSelectIds() {
        return unSelectIds;
    }

    public void setUnSelectIds(BigInteger[] unSelectIds) {
        this.unSelectIds = unSelectIds;
    }

    @Override
    public String toString() {
        return "OrderStatusFinallRequestDto [id=" + id + ", isSelectAll=" + isSelectAll + ", orderStatus=" + orderStatus
                + ", unSelectIds=" + Arrays.toString(unSelectIds) + "]";
    }

    
    
    
}
