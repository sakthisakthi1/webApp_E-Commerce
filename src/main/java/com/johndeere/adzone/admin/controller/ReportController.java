package com.johndeere.adzone.admin.controller;

import com.johndeere.adzone.admin.responsedto.OrderReportResponse;
import com.johndeere.adzone.admin.service.OrderReportService;
import com.johndeere.adzone.responsedto.BasicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/api/OrderReportService")
public class ReportController {
    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    OrderReportService orderReportService;

    @PostMapping("/orders")
    public ResponseEntity<BasicResponse<List<OrderReportResponse>>> getOrderReport() {
        log.info("getOrderReport request called ");
        BasicResponse<List<OrderReportResponse>> result = orderReportService.getOrderReport();

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/ordersbydate")
    public ResponseEntity<BasicResponse<List<OrderReportResponse>>> getOrderReportByDate(@RequestParam String startDate, @RequestParam String endDate) {
        log.info("getOrderReport request called for start date {} , end date  {}", startDate, endDate);
        BasicResponse<List<OrderReportResponse>> result = orderReportService.getOrderReport(startDate, endDate);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/dealers/orders")
    public ResponseEntity<BasicResponse<List<OrderReportResponse>>> getDearOrderReport() {
        log.info("getDearOrderReport request called ");
        BasicResponse<List<OrderReportResponse>> result = orderReportService.getDearOrderReport();

        return ResponseEntity.ok().body(result);
    }

}
