package com.johndeere.adzone.admin.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_drop_down",schema = "\"order\"")
public class OrderStatusDropDown {

    @Id
	@Column(name = "order_drop_down_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger orderDropDownId;

    @Column(name="order_status_value")
    private String orderStatusValue;

    public BigInteger getOrderDropDownId() {
        return orderDropDownId;
    }

    public void setOrderDropDownId(BigInteger orderDropDownId) {
        this.orderDropDownId = orderDropDownId;
    }

    public String getOrderStatusValue() {
        return orderStatusValue;
    }

    public void setOrderStatusValue(String orderStatusValue) {
        this.orderStatusValue = orderStatusValue;
    }
    


















}
