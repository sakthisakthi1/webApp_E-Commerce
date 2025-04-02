package com.johndeere.adzone.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.johndeere.adzone.admin.entity.Order;
import com.johndeere.adzone.admin.entity.OrderStatusDropDown;
import com.johndeere.adzone.admin.requestdto.OrderDto;
import com.johndeere.adzone.admin.requestdto.OrderFilterRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestFilterDto;
import com.johndeere.adzone.admin.requestdto.OrderStatusDropDownRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderStatusFinallRequestDto;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;
import com.johndeere.adzone.admin.service.OrderService;
import com.johndeere.adzone.admin.service.OrderStatusDropDownService;
import com.johndeere.adzone.responsedto.BasicResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.johndeere.adzone.admin.requestdto.OrderRequestDto;
import com.johndeere.adzone.admin.requestdto.OrderRequestFilterDto;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;
import com.johndeere.adzone.admin.service.OrderService;
import com.johndeere.adzone.admin.service.OrderStatusDropDownService;
import com.johndeere.adzone.responsedto.BasicResponse;

@RestController
@RequestMapping("/v1/api/OrderManagementService")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusDropDownService orderStatusDropDownService;

    @Autowired
    private AmazonS3 s3client;

    @Value("${pi.s3.bucket}")
    private String bucketName;

    @Value("${target_s3_folder}")
    private String bucketFolderName;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("/placeOrders/product")
    public ResponseEntity<BasicResponse> addOrdersProduct(
            @Validated @RequestBody List<OrderRequestDto> orderRequestDto) {
        BasicResponse result = orderService.addOrdersProduct(orderRequestDto);
        log.info("OrderManagmentService --> /addOrders " + result);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/placeOrders/Document")
    public ResponseEntity<BasicResponse> addOrderDocument(
            @Validated @RequestBody List<OrderRequestDto> orderRequestDto) {
        BasicResponse result = orderService.addOrdersDocument(orderRequestDto);
        log.info("OrderManagmentService --> /addOrders " + result);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/getMyOrders")
    public ResponseEntity<BasicResponse<Page<OrderResponseDto>>> getOrder(
            @RequestParam(required = false, defaultValue = "0") int page,

            @RequestParam(required = false, defaultValue = "50") int size,

            @RequestParam(required = false, defaultValue = "desc") String sort,

            @RequestParam(required = false, defaultValue = "created_date") String sortBy,

            @RequestBody OrderRequestFilterDto orderRequestFilterDto) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));

        BasicResponse<Page<OrderResponseDto>> result = null;
        String racfId = orderRequestFilterDto.getRacfId();
        result = orderService.getMyOrder(racfId, pageable, orderRequestFilterDto);

        log.info("OrderManagmentService --> /getMyOrder");
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/getAllOrders")
    public ResponseEntity<BasicResponse<Page<OrderResponseDto>>> getAllOrders(
            @RequestParam(required = false, defaultValue = "0") int page,

            @RequestParam(required = false, defaultValue = "50") int size,

            @RequestParam(required = false, defaultValue = "desc") String sort,

            @RequestParam(required = false, defaultValue = "created_date") String sortBy,

            @RequestParam(required = false, defaultValue = "") String search,

            @RequestParam(required = false, defaultValue = "") BigInteger userId,

            @RequestBody OrderFilterRequestDto orderFilterRequestDto) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));

        BasicResponse<Page<OrderResponseDto>> result = null;
        if (search != null && !search.equalsIgnoreCase("") || orderFilterRequestDto != null) {
            result = orderService.getAllOrders(pageable, search, orderFilterRequestDto, userId);
        }
        log.info("OrderManagmentService --> /geAllOrders");
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/updatedAllOrderStatus")
    public ResponseEntity<BasicResponse> updatedAllOrder(
            @Validated @RequestBody OrderStatusFinallRequestDto orderStatusFinallRequestDto) {
        BasicResponse result = orderService.updatedAllOrder(orderStatusFinallRequestDto);
        log.info("OrderManagmentService --> /updatedAllOrder " + result);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/getAllDropDownForStatus")
    public ResponseEntity<BasicResponse> getAllDropDownForStatus() {
        BasicResponse result = orderStatusDropDownService.getAllDropDownForStatus();
        log.info("OrderManagmentService --> /getAllDropDownForStatus " + result);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/generateOrderExcel")
    public ResponseEntity<Resource> getExportExcelFile(@RequestParam(required = false, defaultValue = "0") int page,

            @RequestParam(required = false, defaultValue = "50") int size,

            @RequestParam(required = false, defaultValue = "desc") String sort,

            @RequestParam(required = false, defaultValue = "created_date") String sortBy) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));

        BasicResponse result = orderService.getExportExcelFile(pageable);
        // return result;
        System.out.println("\nFile name" + result.getData().toString());

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
                bucketName + "/" + bucketFolderName, result.getData().toString()).withMethod(HttpMethod.GET);
        URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
        Resource resource = this.resourceLoader.getResource(url.toString());
        List<InputStream> fisList = new ArrayList<InputStream>();
        InputStream fis = null;
        try {
            fis = resource.getInputStream();
            fisList.add(fis);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info("OrderManagmentService --> /getAllDropDownForStatus ");
        orderService.getExportExcelFile(pageable);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + result.getData().toString())
                .contentType(MediaType.parseMediaType("application/Order.ms-excel"))
                .body(resource);

    }

    @PutMapping("/cancelOrder")
    public ResponseEntity<BasicResponse> getCancelOrder(@RequestBody OrderRequestDto orderRequestDto) {

        BasicResponse result = orderService.getcancelOrder(orderRequestDto);
        return ResponseEntity.ok().body(result);
    }

}
