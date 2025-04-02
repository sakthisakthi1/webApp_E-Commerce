package com.johndeere.adzone.admin.service;

import com.johndeere.adzone.responsedto.BasicResponse;

import org.springframework.stereotype.Service;

@Service
public interface OrderStatusDropDownService {

    BasicResponse getAllDropDownForStatus();
    
}
