package com.johndeere.adzone.admin.requestdto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderFilterRequestDto {


    private String orderStatus ; 

    private LocalDateTime fromDate ;

    private LocalDateTime toDate;

    private Integer productFlag;

    public OrderFilterRequestDto(String orderStatus, LocalDateTime fromDate, LocalDateTime toDate, Integer productFlag) {
        this.orderStatus = orderStatus;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.productFlag=productFlag;
    }

    public Integer getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(Integer productFlag) {
        this.productFlag = productFlag;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "OrderFilterRequestDto [fromDate=" + fromDate + ",productFlag="+ productFlag +", orderStatus=" + orderStatus + ", toDate=" + toDate
                + "]";
    }

    public OrderFilterRequestDto() {
        super();
    }

   
}
