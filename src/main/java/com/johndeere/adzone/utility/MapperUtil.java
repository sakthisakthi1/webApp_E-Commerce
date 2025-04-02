package com.johndeere.adzone.utility;

import java.util.List;

import com.johndeere.adzone.admin.entity.UserCart;
import com.johndeere.adzone.admin.requestdto.OrderRequestDto;
import com.johndeere.adzone.admin.requestdto.UserCartRequestDto;

import org.modelmapper.ModelMapper;

public class MapperUtil<Source, Destination> {

    public Destination transfer(OrderRequestDto orderRequest, Class<Destination> destination) {
        try {
            return new ModelMapper().map(orderRequest, destination);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public UserCart transfer(UserCartRequestDto ucrd, Class<UserCart> destination) {
        try {
            return new ModelMapper().map(ucrd, destination);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
}