package com.johndeere.adzone.admin.dao;

import com.johndeere.adzone.admin.responsedto.OrderReportResponse;

import java.util.Date;
import java.util.List;

public interface OrderReportDao {
    List<OrderReportResponse> getAllOrder();

    List<OrderReportResponse> getAllOrder(String startDate, String endDate);

    List<OrderReportResponse> getDearOrderReport();
}
