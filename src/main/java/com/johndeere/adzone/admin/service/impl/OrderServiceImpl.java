package com.johndeere.adzone.admin.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.johndeere.adzone.admin.dao.OrderDao;
import com.johndeere.adzone.admin.entity.DownloadDetails;
import com.johndeere.adzone.admin.entity.Order;
import com.johndeere.adzone.admin.entity.UserCart;
import com.johndeere.adzone.admin.repository.DownloadDetailsRepository;
import com.johndeere.adzone.admin.repository.OrderRepository;
import com.johndeere.adzone.admin.repository.UserCartRepository;
import com.johndeere.adzone.admin.requestdto.NotificationDto;
import com.johndeere.adzone.admin.requestdto.OrderFilterRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestFilterDto;
import com.johndeere.adzone.admin.requestdto.OrderStatusFinallRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderStatusUpdateRequestDto;
import com.johndeere.adzone.admin.responsedto.DocumentGridResponseDto;
import com.johndeere.adzone.admin.responsedto.DownloadFileDetailResponseDto;
import com.johndeere.adzone.admin.responsedto.MarketResponseDto;
import com.johndeere.adzone.admin.responsedto.OrderAddressResponseDto;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;
import com.johndeere.adzone.admin.service.DocumentConnectorService;
import com.johndeere.adzone.admin.service.DownloadService;
import com.johndeere.adzone.admin.service.NotificationConnectorService;
import com.johndeere.adzone.admin.service.OrderService;
import com.johndeere.adzone.config.MessageProperties;
import com.johndeere.adzone.responsedto.BasicResponse;
import com.johndeere.adzone.responsedto.ErrorResponse;
import com.johndeere.adzone.responsedto.SuccessResponse;
import com.johndeere.adzone.utility.DELETE_FLAG;
import com.johndeere.adzone.utility.MapperUtil;
import com.johndeere.adzone.utility.ObjectMapperUtils;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private MapperUtil<OrderRequestDto, Order> orderReqMapper = new MapperUtil<>();

    private MapperUtil<Order, OrderRequestDto> orderMapper = new MapperUtil<>();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DocumentConnectorService documentConnectorService;

    @Autowired
    private MessageProperties messageProperties;

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private FileHandlerService fileHandlerService;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private DownloadDetailsRepository downloadDetailsRepository;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    private NotificationConnectorService notificationConnectorService;

    // @Autowired
    // private DownloadFileDetailResponseDto downloadFileDetailResponseDto;

    @Override
    public BasicResponse<Page<OrderResponseDto>> getMyOrder(String racfId, Pageable pageable,
            OrderRequestFilterDto orderRequestFilterDto) {
        BasicResponse<Page<OrderResponseDto>> basicResponse = new BasicResponse<Page<OrderResponseDto>>();
        Page<OrderResponseDto> orderResponseDtoPage = null;
        // Page<Order> orderList = orderRepository.findByRacfId(racfId, pageable);
        Page<OrderResponseDto> orderlist1 = orderDao.getByRacfId(racfId, pageable, orderRequestFilterDto);
        ErrorResponse error = new ErrorResponse(null, null);
        SuccessResponse message = null;

        List<OrderResponseDto> result = new ArrayList<>();
        try {
            if (!orderlist1.isEmpty()) {
                // orderResponseDtoPage = ObjectMapperUtils.mapEntityPageIntoDtoPage(orderList,
                // OrderResponseDto.class);

                Iterator orderItr = orderlist1.iterator();
                while (orderItr.hasNext()) {
                    OrderResponseDto orderResponseDto = (OrderResponseDto) orderItr.next();

                    if (orderResponseDto.getDocumentFileId() != null) {

                        DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                                .getDocumentFileById(orderResponseDto.getDocumentFileId());

                        orderResponseDto.setDocumentName(documentGridResponseDto.getDocumentFileName());
                        orderResponseDto.setDocumentFileType(documentGridResponseDto.getExtension());
                        orderResponseDto.setDocumentFileSize(documentGridResponseDto.getDocumentFileSize());
                        orderResponseDto.setThumbnailId(documentGridResponseDto.getThumbnailId());
                        orderResponseDto.setThumbnailName(documentGridResponseDto.getThumbnailName());
                        orderResponseDto.setThumbnailUrl(documentGridResponseDto.getThumbnailUrl());
                        // total impl
                        Integer price = orderResponseDto.getOrderAmount();
                        Integer quantity = orderResponseDto.getQuantity();
                        if (quantity != null) {
                            Integer multiply = price * quantity;
                            if (multiply != null) {
                                orderResponseDto.setTotal(multiply);
                            }
                        }
                    }

                    if (orderResponseDto.getAddressId() != null) {

                        OrderAddressResponseDto orderAddressResponseDto = documentConnectorService
                                .getAddressForId(orderResponseDto.getAddressId());
                        orderResponseDto.setAddress(orderAddressResponseDto.getName() + ","
                                + orderAddressResponseDto.getStreetAddress() + "," + orderAddressResponseDto.getCity()
                                + "," + orderAddressResponseDto.getState() + "," + orderAddressResponseDto.getCountry()
                                + "," + orderAddressResponseDto.getPincode().toString() + ","
                                + orderAddressResponseDto.getEmail() + ","
                                + orderAddressResponseDto.getContactNumber().toString());
                    }
                }
                PriorityQueue<OrderResponseDto> priorityQueue = new PriorityQueue<OrderResponseDto>((o1, o2) -> {
                    return o1.getCreatedDate().isAfter(o2.getCreatedDate()) ? 1 : 0;
                });

                Iterator orderItr1 = orderResponseDtoPage.iterator();
                while (orderItr1.hasNext()) {
                    OrderResponseDto orderResponseDto = (OrderResponseDto) orderItr1.next();
                    if (orderRequestFilterDto.getMinPrice() != null && orderRequestFilterDto.getMaxPrice() != null) {
                        int min = orderRequestFilterDto.getMinPrice();
                        int max = orderRequestFilterDto.getMaxPrice();

                        if (orderResponseDto.getOrderAmount() >= min && orderResponseDto.getOrderAmount() <= max) {
                            result.add(orderResponseDto);
                        }

                    } else if (orderRequestFilterDto.getProductFlag() != null
                            && orderRequestFilterDto.getOrderStatusList() != null) {
                        if (orderRequestFilterDto.getProductFlag().equals(orderResponseDto.getProductFlag())
                                && orderRequestFilterDto.getOrderStatusList()
                                        .contains(orderResponseDto.getOrderStatus())) {
                            result.add(orderResponseDto);
                        }
                    } else if (orderRequestFilterDto.getLastNDays() != null) {

                        priorityQueue.add(orderResponseDto);
                    } else if (orderRequestFilterDto.getProductFlag() != null) {
                        if (orderRequestFilterDto.getProductFlag().equals(orderResponseDto.getProductFlag())) {
                            System.out.println("ProductFlagggggg:::::::" + orderResponseDto);
                            result.add(orderResponseDto);
                        }
                    } else if (orderRequestFilterDto.getOrderStatusList() != null
                            && orderRequestFilterDto.getOrderStatusList().size() > 0) {
                        if (orderRequestFilterDto.getOrderStatusList().contains(orderResponseDto.getOrderStatus())) {
                            result.add(orderResponseDto);
                        }
                    } else {
                        result.add(orderResponseDto);
                    }

                }

                if (orderRequestFilterDto.getLastNDays() != null) {
                    int nDays = orderRequestFilterDto.getLastNDays();
                    LocalDate startDay = LocalDate.now().minusDays(nDays);
                    log.info(" start date :: " + startDay);

                    while (!priorityQueue.isEmpty()) {
                        OrderResponseDto orderResponseDto1 = priorityQueue.poll();
                        if (orderResponseDto1.getCreatedDate().toLocalDate().compareTo(startDay) == 1) {
                            result.add(orderResponseDto1);
                        }
                    }
                }
                message = new SuccessResponse(messageProperties.getMyOrderListedSuccessCode(),
                        messageProperties.getMyOrderListedSuccessMessage());
                log.info("OrderService --> getMyOrder : Order List is generated ");
            } else {
                error = new ErrorResponse("-1", "There is no myOrder to see");
                basicResponse.setError(error);
                log.info("OrderService --> getMyOrder : There is no Order to see ");
                return basicResponse;
            }

            Page<OrderResponseDto> orderResponseDtosResult = new PageImpl<>(result, pageable, 1);
            basicResponse.setMessage(message);
            basicResponse.setData(orderResponseDtosResult);
            return basicResponse;

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("OrderService --> getMyOrder " + ex.getMessage());
        } finally {
            orderResponseDtoPage = null;
        }

        return basicResponse;
    }

    @Override
    public BasicResponse addOrdersProduct(List<OrderRequestDto> orderRequestDto) {
        List<Order> orderMapper = new ArrayList<>();
        Order order = new Order();
        BasicResponse basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        log.info("Request for Place the Orders");

        for (OrderRequestDto orderRequest : orderRequestDto) {

            order = orderReqMapper.transfer(orderRequest, Order.class);

            order.setCreatedBy(orderRequest.getRacfId());
            order.setCreatedDate(LocalDateTime.now());
            order.setDeleteFlag(DELETE_FLAG.FALSE.value);
            order.setOrderOn(LocalDateTime.now());
            // order.setDeliveredOn(LocalDateTime.now());
            order.setOrderStatus("Pending");// {Default status for all the printable materials ordered}
            order.setDeleteFlag(0);
            order.setProductFlag(1);// Main condition for type differnt the order item- i.e, for product defaul = 1

            log.info("OrderService --> addorders with order status");

            Order saved = orderRepository.save(order);
            UserCart ucProduct = getDocumentForDeleteFlag(saved.getDocumentFileId(), saved.getRacfId());
            if (ucProduct == null) {
                log.info("item moved from the cart successfully");
            }

            NotificationDto dto = new NotificationDto();
            dto.setOrderId(saved.getOrderId());
            dto.setOrderOn(saved.getOrderOn());
            dto.setOrderNumber(saved.getOrderNumber());
            dto.setOrderStatus(saved.getOrderStatus());
            dto.setRacfId(saved.getRacfId());
            dto.setDeliveredOn(saved.getDeliveredOn());
            dto.setCreatedDate(saved.getCreatedDate());
            dto.setCreatedDBy(saved.getCreatedBy());
            dto.setUpdatedBy(saved.getUpdateBy());
            dto.setMessage("Your Order " + saved.getOrderNumber() + " has been placed successfully.");
            dto.setUpdatedDate(saved.getUpdateDate());
            dto.setQuantity(saved.getQuantity());

            // Order Number Generation
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            SimpleDateFormat month = new SimpleDateFormat("MM");
            SimpleDateFormat date = new SimpleDateFormat("dd");
            Date dateToday = new Date();

            String orderCode = date.format(dateToday) + month.format(dateToday) + year.format(dateToday)
                    + saved.getOrderId();

            Order orderObj = orderRepository.getById(saved.getOrderId());
            BigInteger orderNumber = new BigInteger(orderCode);
            orderObj.setOrderNumber(orderNumber);
            saved = orderRepository.save(orderObj);
            dto.setOrderNumber(orderNumber);
            dto.setDocumentFileId(saved.getDocumentFileId());
            DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                    .getDocumentFile(saved.getDocumentFileId());
            dto.setProductName(documentGridResponseDto.getDocumentFileName());
            try {

                log.info("OrderService --> addorders with order status");

                if (saved != null) {
                    log.info("calling Notification connector service " + saved);
                    notificationConnectorService.saveNotification(dto);

                    orderMapper.add(order);
                    message.setMessageCode(messageProperties.getOrderPlaceProductSuccessCode());
                    message.setMessageDescription(messageProperties.getOrderPlaceProductSuccessMessage());
                    basicResponse.setData(orderMapper);
                    basicResponse.setMessage(message);
                    log.info("OrderService --> addOrderProduct -- product order placed successfully !");
                } else {
                    error.setErroCode("-1");
                    error.setErrorMessage("order could not be placed !");
                    log.error(
                            "OrderService --> addOrderProduct -- addOrderProduct  Order from cart could not placed ! !");
                    basicResponse.setError(error);
                }
            } catch (Exception e) {
                e.printStackTrace();
                error.setErroCode("-1");
                error.setErrorMessage("Order could not be created !");
                log.error("OrderService --> addOrders " + e.getMessage());
                basicResponse.setError(error);
            }
        }

        return basicResponse;

    }

    @Override
    public BasicResponse addOrdersDocument(List<OrderRequestDto> orderRequestDto) {
        ArrayList<Order> orderDocumentMapper = new ArrayList<>();
        Order orderDoc = new Order();
        BasicResponse basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        List<DownloadFileDetailResponseDto> downloadFileDetailResponseDto = new ArrayList<>();
        log.info("Request for Place the Orders");

        for (OrderRequestDto orderRequest : orderRequestDto) {

            orderDoc = orderReqMapper.transfer(orderRequest, Order.class);

            orderDoc.setCreatedBy(orderRequest.getRacfId());
            orderDoc.setCreatedDate(LocalDateTime.now());
            orderDoc.setDeleteFlag(DELETE_FLAG.FALSE.value);
            orderDoc.setOrderOn(LocalDateTime.now());
            orderDoc.setDeliveredOn(LocalDateTime.now());
            orderDoc.setQuantity(1);
            orderDoc.setOrderAmount(0);
            orderDoc.setOrderStatus("Delivered");
            orderDoc.setDeleteFlag(0);
            orderDoc.setProductFlag(0);
            orderDoc.setDocumentFileId(orderRequest.getDocumentFileId());
            // orderDoc.getOrderNumber()

            log.info("OrderService --> addorders with order status");

            Order saved = orderRepository.save(orderDoc); // order Repo

            // Order Number Generation
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            SimpleDateFormat month = new SimpleDateFormat("MM");
            SimpleDateFormat date = new SimpleDateFormat("dd");
            Date dateToday = new Date();

            String orderCode = date.format(dateToday) + month.format(dateToday) + year.format(dateToday)
                    + saved.getOrderId();

            Order orderObj = orderRepository.getById(saved.getOrderId());
            BigInteger orderNumber = new BigInteger(orderCode);
            orderObj.setOrderNumber(orderNumber);
            saved = orderRepository.save(orderObj);
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setOrderId(saved.getOrderId());
            notificationDto.setOrderOn(saved.getOrderOn());
            notificationDto.setOrderNumber(saved.getOrderNumber());
            notificationDto.setOrderStatus(saved.getOrderStatus());
            notificationDto.setRacfId(saved.getRacfId());
            notificationDto.setDeliveredOn(saved.getDeliveredOn());
            notificationDto.setCreatedDate(saved.getCreatedDate());
            notificationDto.setCreatedDBy(saved.getCreatedBy());
            notificationDto.setUpdatedBy(saved.getUpdateBy());
            notificationDto.setMessage("Your Order " + saved.getOrderNumber() + " has been placed successfully.");
            notificationDto.setUpdatedDate(saved.getUpdateDate());
            notificationDto.setQuantity(saved.getQuantity());

            try {

                UserCart uc = getDocumentForDeleteFlag(saved.getDocumentFileId(), saved.getRacfId());
                log.info("OrderService --> addorders with order status");

                if (saved != null) {
                    orderDocumentMapper.add(orderDoc);

                    BigInteger docfileId = orderDoc.getDocumentFileId();

                    DocumentGridResponseDto docFileDetail = documentConnectorService.getDocumentFile(docfileId);

                    String docFileName = docFileDetail.getDocumentFileName();
                    BigInteger docFileId = docFileDetail.getDocumentFileId();

                    DownloadFileDetailResponseDto dto = new DownloadFileDetailResponseDto(docFileName, docFileId);

                    dto.setDocumentFileName(docFileName);
                    dto.setDocumentFileId(docFileId);

                    downloadFileDetailResponseDto.add(dto);

                    notificationDto.setDocumentFileId(docFileId);
                    DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                            .getDocumentFile(saved.getDocumentFileId());
                    notificationDto.setProductName(documentGridResponseDto.getDocumentFileName());
                    notificationConnectorService.saveNotification(notificationDto);

                    message.setMessageCode(messageProperties.getOrderPlaceDocumentSuccessCode());
                    message.setMessageDescription(messageProperties.getOrderPlaceDocumentSuccessMessage());
                    basicResponse.setData(downloadFileDetailResponseDto);
                    basicResponse.setMessage(message);
                    log.info("OrderService --> addOrderDocument --  Document order placed successfully !");
                } else {
                    error.setErroCode("-1");
                    error.setErrorMessage("order could not be placed !");
                    log.error(
                            "OrderService --> addOrderDocument -- addOrderProduct  Order from cart could not placed ! !");
                    basicResponse.setError(error);
                }

            } catch (Exception e) {
                e.printStackTrace();
                error.setErroCode("-1");
                error.setErrorMessage("Order could not be placed !");
                log.error("OrderService --> addOrders " + e.getMessage());
                basicResponse.setError(error);
            }
        }

        return basicResponse;
    }

    public UserCart getDocumentForDeleteFlag(BigInteger Id, String racfId) {
        UserCart usercart = null;
        Optional<UserCart> ucr = userCartRepository.findByDocumentFileIdAndDeleteFlagAndCreatedBy(Id, 0, racfId);

        if (ucr.isPresent()) {
            usercart = ucr.get();
            usercart.setDeleteFlag(1);
        } else {
            return usercart;
        }
        UserCart update = userCartRepository.save(usercart);
        return usercart;
    }

    @Override
    public BasicResponse<Page<OrderResponseDto>> getAllOrders(Pageable pageable, String search,
            OrderFilterRequestDto orderFilterRequestDto, BigInteger userId) {
        BasicResponse<Page<OrderResponseDto>> basicResponse = new BasicResponse<Page<OrderResponseDto>>();
        ErrorResponse error = new ErrorResponse(null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        Page<OrderResponseDto> orderResponseDtoPage = null;
        List<BigInteger> orderListForConnector = new ArrayList<BigInteger>();
        Page<OrderResponseDto> orderList = null;
        List<OrderResponseDto> finalSetOrderList = new ArrayList<OrderResponseDto>();

        if (userId != null) {
            try {
                List<MarketResponseDto> userRsponseDtoWithMarketId = documentConnectorService
                        .getMarketForUserId(userId);
                for (MarketResponseDto mResponse : userRsponseDtoWithMarketId) {
                    orderListForConnector.addAll(documentConnectorService
                            .getOrderIdForMarket(mResponse.getMarketId()));
                }
                for (BigInteger addressId : orderListForConnector) {
                    List<Order> finalSetOrder = orderRepository.getAddressId(addressId);

                    List<OrderResponseDto> order = ObjectMapperUtils.mapAll(finalSetOrder, OrderResponseDto.class);
                    finalSetOrderList.addAll(order);
                    Page<OrderResponseDto> pageOrderResponse = new PageImpl<>(finalSetOrderList);

                    if (pageOrderResponse != null) {

                        orderResponseDtoPage = ObjectMapperUtils.mapEntityPageIntoDtoPage(pageOrderResponse,
                                OrderResponseDto.class);
                        Iterator orderItr = orderResponseDtoPage.iterator();
                        while (orderItr.hasNext()) {
                            OrderResponseDto orderResponseDto = (OrderResponseDto) orderItr.next();

                            if (orderResponseDto.getDocumentFileId() != null) {
                                DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                                        .getDocumentFileById(orderResponseDto.getDocumentFileId());
                                if (documentGridResponseDto != null) {
                                    orderResponseDto.setProductDescription(documentGridResponseDto.getSpecification());
                                    orderResponseDto.setDocumentName(documentGridResponseDto.getDocumentName());
                                    orderResponseDto.setThumbnailUrl(documentGridResponseDto.getThumbnailUrl());
                                    Integer price = orderResponseDto.getOrderAmount();
                                    Integer quantity = orderResponseDto.getQuantity();
                                    if (quantity != null) {
                                        Integer multiply = price * quantity;
                                        if (multiply != null) {
                                            orderResponseDto.setTotal(multiply);
                                        }
                                    }
                                }
                            }
                            if (orderResponseDto.getAddressId() != null) {
                                OrderAddressResponseDto orderAddressResponseDto = documentConnectorService
                                        .getAddressForId(orderResponseDto.getAddressId());
                                orderResponseDto.setAddress(orderAddressResponseDto.getName() + ","
                                        + orderAddressResponseDto.getStreetAddress() + ","
                                        + orderAddressResponseDto.getCity() + "," + orderAddressResponseDto.getState()
                                        + ","
                                        + orderAddressResponseDto.getCountry() + ","
                                        + orderAddressResponseDto.getPincode().toString() + ","
                                        + orderAddressResponseDto.getEmail() + ","
                                        + orderAddressResponseDto.getContactNumber().toString());
                            }
                        }
                    }
                    message.setMessageCode(messageProperties.getMyOrderAdminListedSuccessCode());
                    message.setMessageDescription(messageProperties.getMyOrderAdminLIstedSuccessMessage());
                    basicResponse.setData(orderResponseDtoPage);
                    basicResponse.setMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                orderList = orderDao.findAllOrders(pageable, search, orderFilterRequestDto);
                if (!orderList.isEmpty()) {
                    orderResponseDtoPage = ObjectMapperUtils.mapEntityPageIntoDtoPage(orderList,
                            OrderResponseDto.class);
                    Iterator orderItr = orderResponseDtoPage.iterator();
                    while (orderItr.hasNext()) {
                        OrderResponseDto orderResponseDto = (OrderResponseDto) orderItr.next();

                        if (orderResponseDto.getDocumentFileId() != null) {
                            DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                                    .getDocumentFileById(orderResponseDto.getDocumentFileId());
                            if (documentGridResponseDto != null) {
                                orderResponseDto.setProductDescription(documentGridResponseDto.getSpecification());
                                orderResponseDto.setDocumentName(documentGridResponseDto.getDocumentName());
                                orderResponseDto.setThumbnailUrl(documentGridResponseDto.getThumbnailUrl());
                                Integer price = orderResponseDto.getOrderAmount();
                                Integer quantity = orderResponseDto.getQuantity();
                                if (quantity != null) {
                                    Integer multiply = price * quantity;
                                    if (multiply != null) {
                                        orderResponseDto.setTotal(multiply);
                                    }
                                }
                            }
                            if (orderResponseDto.getAddressId() != null) {
                                OrderAddressResponseDto orderAddressResponseDto = documentConnectorService
                                        .getAddressForId(orderResponseDto.getAddressId());

                                orderResponseDto.setAddress(orderAddressResponseDto.getName() + ","
                                        + orderAddressResponseDto.getStreetAddress() + ","
                                        + orderAddressResponseDto.getCity() + ","
                                        + orderAddressResponseDto.getState()
                                        + ","
                                        + orderAddressResponseDto.getCountry() + ","
                                        + orderAddressResponseDto.getPincode().toString() + ","
                                        + orderAddressResponseDto.getEmail() + ","
                                        + orderAddressResponseDto.getContactNumber().toString());
                            }
                        }
                    }
                    message.setMessageCode(messageProperties.getMyOrderAdminListedSuccessCode());
                    message.setMessageDescription(messageProperties.getMyOrderAdminLIstedSuccessMessage());
                    basicResponse.setData(orderResponseDtoPage);
                    basicResponse.setMessage(message);
                } else {
                    message.setMessageCode("-1");
                    message.setMessageDescription("Order could not be LIsted");
                    basicResponse.setData(orderResponseDtoPage);
                    basicResponse.setMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
                error.setErroCode("-1");
                error.setErrorMessage("Order could not be Listed !");
                log.error("OrderService --> getAllOrders " + e.getMessage());
                basicResponse.setError(error);
            } finally {
                orderList = null;
            }
        }

        return basicResponse;

    }

    @Override
    public BasicResponse getExportExcelFile(Pageable pageable) {

        List<OrderResponseDto> list = new ArrayList();
        BasicResponse basicResponse = new BasicResponse();
        Page<OrderResponseDto> orderList = orderDao.findAllOrders(pageable, null, new OrderFilterRequestDto());
        if (!orderList.isEmpty()) {
            Page<OrderResponseDto> orderPage = ObjectMapperUtils.mapEntityPageIntoDtoPage(orderList,
                    OrderResponseDto.class);

            Iterator orderItr = orderPage.iterator();
            while (orderItr.hasNext()) {
                OrderResponseDto order = (OrderResponseDto) orderItr.next();
                list.add(order);
            }
        }

        List<String> headerList = Arrays.asList("S.No", "Order Number", "Order Date", "Quantity", "Total Price",
                "RACF ID", "Delivery Address", "Delivery Status");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Order Sheet");
            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header
            int i = 0;
            Cell cell;
            for (String header : headerList) {
                cell = row.createCell(i);
                cell.setCellValue(header);
                cell.setCellStyle(headerCellStyle);
                i++;
            }

            AtomicInteger counter = new AtomicInteger(1);
            list.forEach(order -> {
                Row dataRow = sheet.createRow(counter.get());
                dataRow.createCell(0).setCellValue(counter.getAndAdd(1));
                dataRow.createCell(1)
                        .setCellValue(order.getOrderNumber() != null ? order.getOrderNumber().toString() : "");
                dataRow.createCell(2).setCellValue(order.getOrderOn() != null ? order.getOrderOn().toString() : "");
                dataRow.createCell(3).setCellValue(order.getQuantity() != null ? order.getQuantity().toString() : "");
                dataRow.createCell(4)
                        .setCellValue(order.getOrderAmount() != null ? order.getOrderAmount().toString() : "");
                dataRow.createCell(5).setCellValue(order.getRacfId() != null ? order.getRacfId().toString() : "");
                dataRow.createCell(6).setCellValue(order.getAddress() != null ? order.getAddress().toString() : "");
                dataRow.createCell(7)
                        .setCellValue(order.getOrderStatus() != null ? order.getOrderStatus().toString() : "");

            });

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            ByteArrayInputStream byteStream = new ByteArrayInputStream(outputStream.toByteArray());
            String filename = fileHandlerService.makeFileName("Order.xlsx", fileHandlerService.getTimeStamp());
            Boolean isUploaded = fileHandlerService.uploadFileWithByteStream(byteStream, filename);
            DownloadDetails downloadDetails = new DownloadDetails();
            downloadDetails.setS3FileName(filename);
            downloadDetails.setStatusFlag(1);
            if (isUploaded) {
                downloadDetails.setStatusFlag(2);
            } else {
                downloadDetails.setStatusFlag(3);
            }

            downloadDetails = downloadService.updateCreatedByWithRacfId(downloadDetails);
            downloadDetailsRepository.save(downloadDetails);

            basicResponse.setMessage(new SuccessResponse());
            basicResponse.setData(downloadDetails.getS3FileName());
            basicResponse.getMessage().setMessageCode(messageProperties.getOrderExportSuccessCode());
            basicResponse.getMessage().setMessageDescription(messageProperties.getOrderExportSuccessMessage());
        } catch (IOException ex) {

            ex.printStackTrace();
            return null;
        }
        return basicResponse;

    }

    @Override
    public BasicResponse updatedAllOrder(OrderStatusFinallRequestDto orderStatusFinallRequestDto) {
        BasicResponse basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        List<Order> UpdateList = new ArrayList<>();

        List<BigInteger> unSelectedListIDarraylist = new ArrayList<BigInteger>(
                Arrays.asList(orderStatusFinallRequestDto.getUnSelectIds()));

        String tempNotificationUrl = "";
        String previousStatus = "";
        if (orderStatusFinallRequestDto.getIsSelectAll() == 1) {

            List<Order> AllOrders = orderRepository.findAll();
            if (!AllOrders.isEmpty()) {
                for (Order osu : AllOrders) {
                    if (unSelectedListIDarraylist.contains(osu.getOrderId())) {
                        message.setMessageCode("1004");
                        message.setMessageDescription("UnSelected items");
                        basicResponse.setMessage(message);
                    } else {

                        previousStatus = osu.getOrderStatus();
                        osu.setOrderStatus(orderStatusFinallRequestDto.getOrderStatus());
                        Order update = orderRepository.save(osu);

                        NotificationDto dto = new NotificationDto();
                        dto.setOrderId(update.getOrderId());
                        dto.setOrderOn(update.getOrderOn());
                        dto.setOrderNumber(update.getOrderNumber());
                        dto.setOrderStatus(update.getOrderStatus());
                        dto.setRacfId(update.getRacfId());
                        dto.setDeliveredOn(update.getDeliveredOn());
                        dto.setCreatedDate(update.getCreatedDate());
                        dto.setCreatedDBy(update.getCreatedBy());
                        dto.setUpdatedBy(update.getUpdateBy());
                        dto.setMessage("Your Order " + update.getOrderNumber() + " has been placed successfully.");
                        dto.setUpdatedDate(update.getUpdateDate());
                        dto.setQuantity(update.getQuantity());
                        dto.setOrderNumber(update.getOrderNumber());
                        dto.setDocumentFileId(update.getDocumentFileId());
                        DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                                .getDocumentFile(update.getDocumentFileId());
                        dto.setProductName(documentGridResponseDto.getDocumentFileName());
                        notificationConnectorService.updateNotification(dto);
                        message.setMessageCode(messageProperties.getOrderUpdateSuccessCode());
                        message.setMessageDescription(messageProperties.getOrderUpdatedSuccessDescription());
                        basicResponse.setData(update);
                        basicResponse.setMessage(message);
                        log.info("OrderService --> getAllOrders --order listed Successfully !");
                    }
                }

            }
        } else {
            for (OrderStatusUpdateRequestDto ord : orderStatusFinallRequestDto.getId()) {

                Order order = orderRepository.findByOrderId(ord.getOrderId());
                previousStatus = order.getOrderStatus();
                order.setOrderStatus(ord.getOrderStatus());
                Order update = orderRepository.save(order);

                if (update != null) {
                    UpdateList.add(order);

                    NotificationDto dto = new NotificationDto();
                    dto.setOrderId(update.getOrderId());
                    dto.setOrderOn(update.getOrderOn());
                    dto.setOrderNumber(update.getOrderNumber());
                    dto.setOrderStatus(update.getOrderStatus());
                    dto.setRacfId(update.getRacfId());
                    dto.setDeliveredOn(update.getDeliveredOn());
                    dto.setCreatedDate(update.getCreatedDate());
                    dto.setCreatedDBy(update.getCreatedBy());
                    dto.setUpdatedBy(update.getUpdateBy());
                    dto.setMessage("Your Order " + update.getOrderNumber() + " has been placed successfully.");
                    dto.setUpdatedDate(update.getUpdateDate());
                    dto.setQuantity(update.getQuantity());
                    dto.setOrderNumber(update.getOrderNumber());
                    dto.setDocumentFileId(update.getDocumentFileId());
                    DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                            .getDocumentFile(update.getDocumentFileId());
                    dto.setProductName(documentGridResponseDto.getDocumentFileName());
                    notificationConnectorService.updateNotification(dto);
                    message.setMessageCode(messageProperties.getOrderUpdateSuccessCode());
                    message.setMessageDescription(messageProperties.getOrderUpdatedSuccessDescription());
                    basicResponse.setData(UpdateList);
                    basicResponse.setMessage(message);
                    log.info("OrderService --> getAllOrders --order listed Successfully !");
                } else {
                    message.setMessageCode("-1");
                    message.setMessageDescription("Order could not be LIsted");
                    basicResponse.setMessage(message);
                    log.info("OrderService --> getAllOrders --order could not listed !");
                }
            }
        }
        return basicResponse;

    }

    @Override
    public BasicResponse getcancelOrder(OrderRequestDto orderRequestDto) {
        BasicResponse basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);

        BigInteger orderId = orderRequestDto.getOrderId();
        Order getOrder = orderRepository.findById(orderId).orElse(null);
        getOrder.setOrderStatus("Cancelled");

        if (getOrder == null) {
            error.setErrorMessage("Order not found");
        }

        getOrder = orderRepository.save(getOrder);
        Order update = orderRepository.save(getOrder);
        String status = "Your Order " + update.getOrderNumber() + "has been Cancelled  successfully.";

        NotificationDto dto = new NotificationDto();
        dto.setOrderId(update.getOrderId());
        dto.setOrderOn(update.getOrderOn());
        dto.setOrderNumber(update.getOrderNumber());
        dto.setOrderStatus(update.getOrderStatus());
        dto.setRacfId(update.getRacfId());
        dto.setDeliveredOn(update.getDeliveredOn());
        dto.setCreatedDate(update.getCreatedDate());
        dto.setCreatedDBy(update.getCreatedBy());
        dto.setUpdatedBy(update.getUpdateBy());
        dto.setMessage("Your Order " + update.getOrderNumber() + " has been cancelled successfully.");
        dto.setUpdatedDate(update.getUpdateDate());
        dto.setQuantity(update.getQuantity());
        dto.setOrderNumber(update.getOrderNumber());
        dto.setDocumentFileId(update.getDocumentFileId());
        DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                .getDocumentFile(update.getDocumentFileId());
        dto.setProductName(documentGridResponseDto.getDocumentFileName());
        notificationConnectorService.updateNotification(dto);
        message.setMessageCode("Your Order" + orderId + "has been Cancelled  successfully.");
        basicResponse.setData(getOrder);
        basicResponse.setError(error);
        basicResponse.setMessage(message);
        return basicResponse;
    }

}