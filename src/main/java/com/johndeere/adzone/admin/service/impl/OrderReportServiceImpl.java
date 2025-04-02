package com.johndeere.adzone.admin.service.impl;

import com.johndeere.adzone.admin.dao.OrderReportDao;
import com.johndeere.adzone.admin.entity.Order;
import com.johndeere.adzone.admin.requestdto.OrderRequestDto;
import com.johndeere.adzone.admin.responsedto.OrderReportResponse;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;
import com.johndeere.adzone.admin.service.OrderReportService;
import com.johndeere.adzone.responsedto.BasicResponse;
import com.johndeere.adzone.responsedto.ErrorResponse;
import com.johndeere.adzone.responsedto.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderReportServiceImpl implements OrderReportService {

    private static final Logger log = LoggerFactory.getLogger(OrderReportServiceImpl.class);

    @Autowired
    OrderReportDao orderReportDao;

    @Override
    public BasicResponse<List<OrderReportResponse>> getOrderReport() {

        BasicResponse<List<OrderReportResponse>> basicResponse = new BasicResponse<>();

        ErrorResponse error = new ErrorResponse(null, null);
        SuccessResponse message = null;
        try {

            List<OrderReportResponse> orderReportResponseList = orderReportDao.getAllOrder();

            message = new SuccessResponse("200", "Order report");

            basicResponse.setData(orderReportResponseList);
            basicResponse.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("OrderReportServiceImpl --> getOrderReport " + ex.getMessage());
            error = new ErrorResponse("-1", "ERROR");
            basicResponse.setError(error);
        }

        return basicResponse;

    }

    @Override
    public BasicResponse<List<OrderReportResponse>> getOrderReport(String startDate, String endDate) {
        BasicResponse<List<OrderReportResponse>> basicResponse = new BasicResponse<>();

        ErrorResponse error = new ErrorResponse(null, null);
        SuccessResponse message = null;
        try {
            List<OrderReportResponse> orderReportResponseList = orderReportDao.getAllOrder(startDate, endDate);

            message = new SuccessResponse("200", "Order report");

            basicResponse.setData(orderReportResponseList);
            basicResponse.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("OrderReportServiceImpl --> getOrderReport " + ex.getMessage());
            error = new ErrorResponse("-1", "ERROR");
            basicResponse.setError(error);
        }

        return basicResponse;
    }

    @Override
    public BasicResponse<List<OrderReportResponse>> getDearOrderReport() {
        BasicResponse<List<OrderReportResponse>> basicResponse = new BasicResponse<>();
        ErrorResponse error = new ErrorResponse(null, null);
        SuccessResponse message = null;
        try {
            List<OrderReportResponse> orderReportResponseList = orderReportDao.getDearOrderReport();

            message = new SuccessResponse("200", "Order report");

            basicResponse.setData(orderReportResponseList);
            basicResponse.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("OrderReportServiceImpl --> getOrderReport " + ex.getMessage());
            error = new ErrorResponse("-1", "ERROR");
            basicResponse.setError(error);
        }

        return basicResponse;
    }
}
