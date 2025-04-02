package com.johndeere.adzone.admin.service;

import java.math.BigInteger;
import java.util.List;

import com.johndeere.adzone.admin.entity.Order;
import com.johndeere.adzone.admin.requestdto.OrderDto;
import com.johndeere.adzone.admin.requestdto.OrderFilterRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestFilterDto;
import com.johndeere.adzone.admin.requestdto.OrderStatusFinallRequestDto;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;
import com.johndeere.adzone.responsedto.BasicResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

        BasicResponse addOrdersProduct(List<OrderRequestDto> orderRequestDto);

        BasicResponse<Page<OrderResponseDto>> getMyOrder(String racfId, Pageable pageable,
                        OrderRequestFilterDto orderRequestFilterDto);

        BasicResponse addOrdersDocument(List<OrderRequestDto> orderRequestDto);

        BasicResponse<Page<OrderResponseDto>> getAllOrders(Pageable pageable, String search,
                        OrderFilterRequestDto orderFilterRequestDto, BigInteger userId);

        BasicResponse getExportExcelFile(Pageable pageable);

        BasicResponse updatedAllOrder(OrderStatusFinallRequestDto orderStatusFinallRequestDto);

        BasicResponse getcancelOrder(OrderRequestDto orderRequestDto);

}
