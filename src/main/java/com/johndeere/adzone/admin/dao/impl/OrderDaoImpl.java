package com.johndeere.adzone.admin.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.johndeere.adzone.admin.dao.OrderDao;
import com.johndeere.adzone.admin.entity.Order;
import com.johndeere.adzone.admin.requestdto.OrderFilterRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestFilterDto;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;
import com.johndeere.adzone.admin.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public Page<OrderResponseDto> findAllOrders(Pageable pageable, String search,
            OrderFilterRequestDto orderFilterRequestDto) {
        String queryString = " SELECT \n"
                + " mjo.order_id as orderId, \n"
                + "mjo.order_on as orderOn, \n"
                + " mjo.quantity as qantity,\n"
                + " mjo.delivered_on as deliverdOn, \n"
                + " mjo.order_amount  as orderAmount, \n"
                + "mjo.document_id  as documentId,\n"
                + "mjo.delete_flag as deleteFlag,\n"
                + "mjo.created_by  as createdBy ,\n"
                + "mjo.created_date  as createdDate,\n"
                + "mjo.order_status  as orderStatus,\n"
                + "mjo.racfid  as racfId,\n"
                + "mjo.update_by as updaetedBy,\n"
                + "mjo.updated_date as updatedDate,\n"
                + "mjo.order_number as orderNumber,\n"
                + "mjo.product_flag as productFlag,\n"
                + "mjo.address as address,\n"
                + "mjo.document_file_id  as documentFileId, \n "
                + "mjo.address_id as addressId "
                + "  from \"order\".m_jd_orders mjo where mjo .delete_flag =0 \n ";

        queryString = addSearch(queryString, search, orderFilterRequestDto);

        queryString = sortBy(queryString, pageable);

        Query query = entityManager.createNativeQuery(queryString);

        setSearchParameter(queryString, query, search, orderFilterRequestDto);

        long count = query.getResultList().size();
        query.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
        List<OrderResponseDto> orderResponseDtoTemp = new ArrayList<OrderResponseDto>();
        List<Object[]> response = query.getResultList();
        Iterator<Object[]> itr = response.iterator();

        while (itr.hasNext()) {
            Object[] item = itr.next();
            OrderResponseDto orderResponseDto = new OrderResponseDto();

            orderResponseDto.setOrderId((BigInteger) item[0]);
            Timestamp timeStamp = (Timestamp) item[1];
            if (timeStamp != null)
                orderResponseDto.setOrderOn(timeStamp.toLocalDateTime());
            orderResponseDto.setQuantity((Integer) item[2]);
            Timestamp timeStamp1 = (Timestamp) item[3];
            if (timeStamp1 != null)
                orderResponseDto.setDeliveredOn(timeStamp1.toLocalDateTime());
            orderResponseDto.setOrderAmount((Integer) item[4]);
            orderResponseDto.setDocumentId((BigInteger) item[5]);
            orderResponseDto.setDeleteFlag((Integer) item[6]);
            orderResponseDto.setCreatedBy((String) item[7]);
            Timestamp timeStamp2 = (Timestamp) item[8];
            if (timeStamp2 != null)
                orderResponseDto.setCreatedDate(timeStamp2.toLocalDateTime());
            orderResponseDto.setOrderStatus((String) item[9]);
            orderResponseDto.setRacfId((String) item[10]);
            orderResponseDto.setUpdateBy((String) item[11]);
            Timestamp timeStamp3 = (Timestamp) item[12];
            if (timeStamp3 != null)
                orderResponseDto.setUpdatedDate(timeStamp3.toLocalDateTime());
            BigInteger orderNumber = (BigInteger) item[13];
            if (orderNumber != null)
                orderResponseDto.setOrderNumber(orderNumber);
            orderResponseDto.setProductFlag((Integer) item[14]);
            orderResponseDto.setAddress((String) item[15]);
            orderResponseDto.setDocumentFileId((BigInteger) item[16]);
            orderResponseDto.setAddressId((BigInteger) item[17]);
            orderResponseDtoTemp.add(orderResponseDto);

        }
        return new PageImpl<>(orderResponseDtoTemp, pageable, count);
    }

    private void setSearchParameter(String queryString, Query query, String search,
            OrderFilterRequestDto orderFilterRequestDto) {

        if (queryString.contains(":orderStatus")) {
            query.setParameter("orderStatus", (orderFilterRequestDto.getOrderStatus()));
        }
        if (queryString.contains(":productFlag")) {
            query.setParameter("productFlag", (orderFilterRequestDto.getProductFlag()));
        }

        if (queryString.contains(":search")) {
            query.setParameter("search", "%" + search + "%");
        }

        if (queryString.contains(":fromDate") && queryString.contains(":toDate")) {
            if (!orderFilterRequestDto.getFromDate().equals(orderFilterRequestDto.getToDate())) {
                query.setParameter("fromDate",
                        CommonService.getPlusSeconds(orderFilterRequestDto.getFromDate(), 19800L));
                query.setParameter("toDate", CommonService.getPlusSeconds(orderFilterRequestDto.getToDate(), 19800L));
            } else {
                query.setParameter("fromDate",
                        CommonService.getPlusSeconds(orderFilterRequestDto.getFromDate(), 19800L));
                query.setParameter("toDate", CommonService.getPlusSeconds(orderFilterRequestDto.getToDate(), 19800L));
            }
        }

    }

    private String addSearch(String queryString, String search, OrderFilterRequestDto orderFilterRequestDto) {

        if (orderFilterRequestDto.getOrderStatus() != null) {
            queryString += " and mjo.order_status  = :orderStatus";
        }

        if (search != null && !search.isEmpty()) {
            queryString += " and mjo.created_by ilike :search or CAST(mjo.order_number AS TEXT) ilike :search";
        }

        if (orderFilterRequestDto.getFromDate() != null) {
            queryString += " and date(mjo.created_date) >= to_date(:fromDate,'yyyy-MM-dd')";
        }

        if (orderFilterRequestDto.getToDate() != null) {
            queryString += " and date(mjo.created_date) <= to_date(:toDate,'yyyy-MM-dd')";
        }
        if (orderFilterRequestDto.getProductFlag() != null) {
            queryString += " and mjo.product_flag = :productFlag";
        }

        return queryString;
    }

    private String sortBy(String queryString, Pageable pageable) {
        Sort sort = pageable.getSort();
        for (Sort.Order order : sort) {

            if (order.getProperty().equalsIgnoreCase("order_on")) {
                queryString = queryString + " ORDER BY mjo.order_on";
            } else if (order.getProperty().equalsIgnoreCase("created_date")) {
                queryString = queryString + " ORDER BY mjo.created_date";
            } else {
                queryString = queryString + " ORDER BY mjo.created_date";
            }
            // record asc or desc based on the input from screen
            if (order.getDirection().isAscending()) {
                queryString = queryString + " ASC";
            } else {
                queryString = queryString + " DESC";
            }

            break;
        }
        return queryString;
    }

    @Override
    public Page<OrderResponseDto> getByRacfId(String racfId, Pageable pageable,
            OrderRequestFilterDto orderRequestFilterDto) {
        String queryString = " SELECT \n"
                + " mjo.order_id as orderId, \n"
                + "mjo.order_on as orderOn, \n"
                + " mjo.quantity as qantity,\n"
                + " mjo.delivered_on as deliverdOn, \n"
                + " mjo.order_amount  as orderAmount, \n"
                + "mjo.document_id  as documentId,\n"
                + "mjo.delete_flag as deleteFlag,\n"
                + "mjo.created_by  as createdBy ,\n"
                + "mjo.created_date  as createdDate,\n"
                + "mjo.order_status  as orderStatus,\n"
                + "mjo.racfid  as racfId,\n"
                + "mjo.update_by as updaetedBy,\n"
                + "mjo.updated_date as updatedDate,\n"
                + "mjo.order_number as orderNumber,\n"
                + "mjo.product_flag as productFlag,\n"
                + "mjo.address as address,\n"
                + "mjo.document_file_id  as documentFileId, \n "
                + "mjo.address_id as addressId "
                + "from \"order\".m_jd_orders mjo where mjo .delete_flag =0 \n "
                + "and mjo.racfid =" + racfId;

        queryString = addsearch(queryString, orderRequestFilterDto);

        queryString = sortby(queryString, pageable);

        Query query = entityManager.createNativeQuery(queryString);

        setsearchParameter(queryString, query, orderRequestFilterDto);

        long count = query.getResultList().size();
        query.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
        List<OrderResponseDto> orderResponseDtoTemp = new ArrayList<OrderResponseDto>();
        List<Object[]> response = query.getResultList();
        Iterator<Object[]> itr = response.iterator();

        while (itr.hasNext()) {
            Object[] item = itr.next();
            OrderResponseDto orderResponseDto = new OrderResponseDto();

            orderResponseDto.setOrderId((BigInteger) item[0]);
            Timestamp timeStamp = (Timestamp) item[1];
            if (timeStamp != null)
                orderResponseDto.setOrderOn(timeStamp.toLocalDateTime());
            orderResponseDto.setQuantity((Integer) item[2]);
            Timestamp timeStamp1 = (Timestamp) item[3];
            if (timeStamp1 != null)
                orderResponseDto.setDeliveredOn(timeStamp1.toLocalDateTime());
            orderResponseDto.setOrderAmount((Integer) item[4]);
            orderResponseDto.setDocumentId((BigInteger) item[5]);
            orderResponseDto.setDeleteFlag((Integer) item[6]);
            orderResponseDto.setCreatedBy((String) item[7]);
            Timestamp timeStamp2 = (Timestamp) item[8];
            if (timeStamp2 != null)
                orderResponseDto.setCreatedDate(timeStamp2.toLocalDateTime());
            orderResponseDto.setOrderStatus((String) item[9]);
            orderResponseDto.setRacfId((String) item[10]);
            orderResponseDto.setUpdateBy((String) item[11]);
            Timestamp timeStamp3 = (Timestamp) item[12];
            if (timeStamp3 != null)
                orderResponseDto.setUpdatedDate(timeStamp3.toLocalDateTime());
            BigInteger orderNumber = (BigInteger) item[13];
            if (orderNumber != null)
                orderResponseDto.setOrderNumber(orderNumber);
            orderResponseDto.setProductFlag((Integer) item[14]);
            orderResponseDto.setAddress((String) item[15]);
            orderResponseDto.setDocumentFileId((BigInteger) item[16]);
            orderResponseDto.setAddressId((BigInteger) item[17]);
            orderResponseDtoTemp.add(orderResponseDto);

        }
        return new PageImpl<>(orderResponseDtoTemp, pageable, count);
    }

    private void setsearchParameter(String queryString, Query query,
            OrderRequestFilterDto orderRequestFilterDto) {

        if (queryString.contains(":productFlag")) {
            query.setParameter("productFlag", (orderRequestFilterDto.getProductFlag()));
        }
        if (queryString.contains(":lastNDays")) {
            query.setParameter("lastNDays", (orderRequestFilterDto.getLastNDays()));
        }
        if (queryString.contains(":minPrice")) {
            query.setParameter("minPrice", orderRequestFilterDto.getMinPrice());
        }
        if (queryString.contains(":maxPrice")) {
            query.setParameter("maxPrice", orderRequestFilterDto.getMaxPrice());
        }
    }

    private String addsearch(String queryString, OrderRequestFilterDto orderRequestFilterDto) {

        if (orderRequestFilterDto.getProductFlag() != null) {
            queryString += "and mjo.product_flag = :productFlag";
        }
        if (orderRequestFilterDto.getLastNDays() != null) {
            queryString += "and mjo.created_date  > now() - interval ':lastNDays' day";
        }
        if (orderRequestFilterDto.getMaxPrice() != null && orderRequestFilterDto.getMinPrice() != null) {
            queryString += "and mjo.order_amount BETWEEN :minPrice and :maxPrice";
        }
        return queryString;
    }

    private String sortby(String queryString, Pageable pageable) {
        Sort sort = pageable.getSort();
        for (Sort.Order order : sort) {

            if (order.getProperty().equalsIgnoreCase("order_on")) {
                queryString = queryString + " ORDER BY mjo.order_on";
            } else if (order.getProperty().equalsIgnoreCase("created_date")) {
                queryString = queryString + " ORDER BY mjo.created_date";
            } else {
                queryString = queryString + " ORDER BY mjo.created_date";
            }
            // record asc or desc based on the input from screen
            if (order.getDirection().isAscending()) {
                queryString = queryString + " ASC";
            } else {
                queryString = queryString + " DESC";
            }

            break;
        }
        return queryString;
    }

}
