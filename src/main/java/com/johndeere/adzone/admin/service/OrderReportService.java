package com.johndeere.adzone.admin.service;

import com.johndeere.adzone.admin.responsedto.OrderReportResponse;
import com.johndeere.adzone.responsedto.BasicResponse;

import java.util.Date;
import java.util.List;

public interface OrderReportService {
    BasicResponse<List<OrderReportResponse>> getOrderReport();
    BasicResponse<List<OrderReportResponse>> getOrderReport(String startDate, String endDate);

    BasicResponse<List<OrderReportResponse>> getDearOrderReport();
}
