package com.johndeere.adzone.admin.dao.impl;

import com.johndeere.adzone.admin.dao.OrderReportDao;
import com.johndeere.adzone.admin.responsedto.OrderReportResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderReportImpl implements OrderReportDao {

    private static final Logger log = LoggerFactory.getLogger(OrderReportImpl.class);

    @Autowired
    EntityManager entityManager;

    @Override
    public List<OrderReportResponse> getAllOrder() {
        try {
            String queryString = "select  mjo.document_id ,mjo.document_file_id  ,mjo.racfid ,mjo.created_date from  \"order\".m_jd_orders mjo ;\n ";

            Query query = entityManager.createNativeQuery(queryString);
            long count = query.getResultList().size();
            List<OrderReportResponse> orderReportResponseList = new ArrayList<>();
            List<Object[]> response = query.getResultList();
            Iterator<Object[]> itr = response.iterator();

            while (itr.hasNext()) {
                Object[] item = itr.next();
                OrderReportResponse orderReportResponse = new OrderReportResponse();

                orderReportResponse.setDocumentId((BigInteger) item[0]);
                orderReportResponse.setDocumentFileId((BigInteger) item[1]);
                orderReportResponse.setRacfId((String) item[2]);
                orderReportResponse.setCreatedAt((Timestamp) item[3]);
                orderReportResponseList.add(orderReportResponse);
            }
            return orderReportResponseList;
        } catch (Exception exception) {
            log.error("Exception occurred", exception);
        }
        return new ArrayList<>();
    }

    @Override
    public List<OrderReportResponse> getAllOrder(String startDate, String endDate) {
        try {
            /*String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String startDateString = simpleDateFormat.format(startDate);
            String endDateString = simpleDateFormat.format(endDate);*/

            String queryString = "select  mjo.document_id ,mjo.document_file_id  ,mjo.racfid ,mjo.created_date from  \"order\".m_jd_orders mjo " + " where  mjo.created_date between '" + startDate + "' and '" + endDate + "';\n ";

            Query query = entityManager.createNativeQuery(queryString);
            long count = query.getResultList().size();
            List<OrderReportResponse> orderReportResponseList = new ArrayList<>();
            List<Object[]> response = query.getResultList();
            Iterator<Object[]> itr = response.iterator();

            while (itr.hasNext()) {
                Object[] item = itr.next();
                OrderReportResponse orderReportResponse = new OrderReportResponse();

                orderReportResponse.setDocumentId((BigInteger) item[0]);
                orderReportResponse.setDocumentFileId((BigInteger) item[1]);
                orderReportResponse.setRacfId((String) item[2]);
                orderReportResponse.setCreatedAt((Timestamp) item[3]);
                orderReportResponseList.add(orderReportResponse);
            }
            return orderReportResponseList;
        } catch (Exception exception) {
            log.error("Exception occurred", exception);
        }

        return new ArrayList<>();
    }

    @Override
    public List<OrderReportResponse> getDearOrderReport() {
        try {
            String queryString = "select  mjo.document_id ,mjo.document_file_id ,\n" + "mjo.racfid , mjo.created_date\n" + "from  \"order\".m_jd_orders mjo where mjo.racfid like 'X%' ";

            Query query = entityManager.createNativeQuery(queryString);
            long count = query.getResultList().size();
            List<OrderReportResponse> orderReportResponseList = new ArrayList<>();
            List<Object[]> response = query.getResultList();
            Iterator<Object[]> itr = response.iterator();

            while (itr.hasNext()) {
                Object[] item = itr.next();
                OrderReportResponse orderReportResponse = new OrderReportResponse();

                orderReportResponse.setDocumentId((BigInteger) item[0]);
                orderReportResponse.setDocumentFileId((BigInteger) item[1]);
                orderReportResponse.setRacfId((String) item[2]);
                orderReportResponse.setCreatedAt((Timestamp) item[3]);
                orderReportResponseList.add(orderReportResponse);
            }
            return orderReportResponseList;
        } catch (Exception exception) {
            log.error("Exception occurred", exception);
        }
        return new ArrayList<>();
    }
}
