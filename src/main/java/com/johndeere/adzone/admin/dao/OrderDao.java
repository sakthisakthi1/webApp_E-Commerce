package com.johndeere.adzone.admin.dao;

import com.johndeere.adzone.admin.entity.Order;
import com.johndeere.adzone.admin.requestdto.OrderFilterRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestFilterDto;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDao {

    Page<OrderResponseDto> findAllOrders(Pageable pageable, String search, OrderFilterRequestDto orderFilterRequestDto);

    Page<OrderResponseDto> getByRacfId(String racfId, Pageable pageable, OrderRequestFilterDto orderRequestFilterDto);

}
