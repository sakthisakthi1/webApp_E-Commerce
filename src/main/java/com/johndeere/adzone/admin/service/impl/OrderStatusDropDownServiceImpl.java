package com.johndeere.adzone.admin.service.impl;

import java.util.List;

import com.johndeere.adzone.admin.entity.OrderStatusDropDown;
import com.johndeere.adzone.admin.repository.OrderStatusDropDownRepository;
import com.johndeere.adzone.admin.requestdto.OrderStatusDropDownRequestDto;
import com.johndeere.adzone.admin.responsedto.OrderStatusDropDownResponseDto;
import com.johndeere.adzone.admin.service.OrderStatusDropDownService;
import com.johndeere.adzone.responsedto.BasicResponse;
import com.johndeere.adzone.responsedto.ErrorResponse;
import com.johndeere.adzone.responsedto.SuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderStatusDropDownServiceImpl implements OrderStatusDropDownService {

    private static final Logger log = LoggerFactory.getLogger(OrderStatusDropDownServiceImpl.class);

    @Autowired
    private  OrderStatusDropDownRepository orderStatusDropDownRepository;

    @Override
    public BasicResponse getAllDropDownForStatus() {
        BasicResponse<List<OrderStatusDropDown>> basicResponse = new BasicResponse<List<OrderStatusDropDown>>();
		ErrorResponse error = new ErrorResponse(null, null);
		SuccessResponse message = new SuccessResponse(null, null);

		List<OrderStatusDropDown> orderStatusDropDown = orderStatusDropDownRepository.findAll();

        if (orderStatusDropDown != null) {
            
            // message.setMessageCode();
            // message.setMessageDescription();
            basicResponse.setData(orderStatusDropDown);
            basicResponse.setMessage(message);
            log.info("OrderService --> addOrderProduct -- product order placed successfully !");
        } else {
            error.setErroCode("-1");
            error.setErrorMessage("order could not be placed !");
            log.error("OrderService --> addOrderProduct -- addOrderProduct  Order from cart could not placed ! !");
            basicResponse.setError(error);
        }






        return basicResponse;

		
    }





























    
}
