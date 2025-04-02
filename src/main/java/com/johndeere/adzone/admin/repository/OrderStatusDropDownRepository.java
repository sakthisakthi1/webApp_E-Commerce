package com.johndeere.adzone.admin.repository;


import java.math.BigInteger;

import com.johndeere.adzone.admin.entity.OrderStatusDropDown;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderStatusDropDownRepository extends JpaRepository<OrderStatusDropDown, BigInteger> {

    
    
}
