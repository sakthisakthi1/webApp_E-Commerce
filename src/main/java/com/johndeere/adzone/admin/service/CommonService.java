package com.johndeere.adzone.admin.service;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonService {

    private static final Logger log = LoggerFactory.getLogger(CommonService.class);


    //Function to return the filter Date with plus sec
    public static LocalDateTime getPlusSeconds(LocalDateTime toDate,Long seconds){
        return LocalDateTime.from(toDate.plusSeconds(seconds));
    }

 
}
