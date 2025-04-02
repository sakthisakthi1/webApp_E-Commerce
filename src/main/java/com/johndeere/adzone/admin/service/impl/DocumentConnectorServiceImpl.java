package com.johndeere.adzone.admin.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.johndeere.adzone.admin.responsedto.DocumentGridResponseDto;
import com.johndeere.adzone.admin.responsedto.MarketResponseDto;
import com.johndeere.adzone.admin.responsedto.OrderAddressResponseDto;
import com.johndeere.adzone.admin.responsedto.ProductResponseDto;
import com.johndeere.adzone.admin.service.DocumentConnectorService;
import com.johndeere.adzone.responsedto.BasicResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class DocumentConnectorServiceImpl implements DocumentConnectorService {

    private static final Logger log = LoggerFactory.getLogger(DocumentConnectorServiceImpl.class);

    @Autowired
    protected RestTemplate restTemplate;

    @Value("${document.service.url}")
    private String documentServiceUrl;

    @Value("${wishlist.service.url}")
    private String wishlistServiceUrl;

    @Value("${product.service.url}")
    private String productServicerUrl;

    @Value("${address.service.url}")
    private String addressServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Override
    public DocumentGridResponseDto getDocumentFile(BigInteger docfileId) {

        DocumentGridResponseDto documentGridResponseDto = new DocumentGridResponseDto();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // httpEnitity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String url = documentServiceUrl + "/documentFileName/" + docfileId;
        ResponseEntity<BasicResponse<DocumentGridResponseDto>> response = restTemplate.exchange(url, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<BasicResponse<DocumentGridResponseDto>>() {
                });

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getBody())
                && response.getStatusCodeValue() == 200 && !ObjectUtils.isEmpty(response.getBody().getMessage())) {
            BasicResponse basicRes = response.getBody();
            documentGridResponseDto = response.getBody().getData();
            log.info("docFileName::::::::" + documentGridResponseDto.getDocumentFileName());

        }
        return documentGridResponseDto;

    }

    @Override
    public boolean addWishList(BigInteger documentId, String racfId, BigInteger documentFileId, Integer productFlag) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
        String url = wishlistServiceUrl + "/add?documentId=" + documentId + "&racfId=" + racfId + "&documentFileId="
                + documentFileId + "&productFlag=" + productFlag;
        ResponseEntity<BasicResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<BasicResponse>() {
                });

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getBody())
                && response.getStatusCodeValue() == 200) {
            return true;

        }
        return false;
    }

    @Override
    public DocumentGridResponseDto getDocumentFileById(BigInteger documentFileId) {

        DocumentGridResponseDto documentGridResponseDto = new DocumentGridResponseDto();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // httpEnitity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String url = documentServiceUrl + "/getDocumentFileDetailById?documentFileId=" + documentFileId;
        ResponseEntity<BasicResponse<DocumentGridResponseDto>> response = restTemplate.exchange(url, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<BasicResponse<DocumentGridResponseDto>>() {
                });

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getBody())
                && response.getStatusCodeValue() == 200 && !ObjectUtils.isEmpty(response.getBody().getMessage())) {
            BasicResponse basicRes = response.getBody();
            documentGridResponseDto = response.getBody().getData();
        }
        return documentGridResponseDto;
    }

    @Override
    public ProductResponseDto getProductDescripition(BigInteger documentId) {

        ProductResponseDto productResponseDto = new ProductResponseDto();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // httpEnitity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String url = productServicerUrl + "/getProductById?documentId=" + documentId;
        ResponseEntity<BasicResponse<ProductResponseDto>> response = restTemplate.exchange(url, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<BasicResponse<ProductResponseDto>>() {
                });

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getBody())
                && response.getStatusCodeValue() == 200 && !ObjectUtils.isEmpty(response.getBody().getMessage())) {
            BasicResponse basicRes = response.getBody();
            productResponseDto = response.getBody().getData();
        }
        return productResponseDto;
    }

    @Override
    public OrderAddressResponseDto getAddressForId(BigInteger addressId) {

        OrderAddressResponseDto orderResponseDto = new OrderAddressResponseDto();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // httpEnitity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String url = addressServiceUrl + "/getAddressForId/" + addressId;

        ResponseEntity<BasicResponse<OrderAddressResponseDto>> response = restTemplate.exchange(url, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<BasicResponse<OrderAddressResponseDto>>() {
                });

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getBody())
                && response.getStatusCodeValue() == 200 && !ObjectUtils.isEmpty(response.getBody().getMessage())) {
            BasicResponse basicRes = response.getBody();
            orderResponseDto = response.getBody().getData();
        }
        return orderResponseDto;
    }

    @Override
    public List<MarketResponseDto> getMarketForUserId(BigInteger userId) {
        List<MarketResponseDto> marketResponseDto = new ArrayList<MarketResponseDto>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // httpEnitity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String url = userServiceUrl + "/getMarketsMappedToUserByUserId?userId=" + userId;
        ResponseEntity<BasicResponse<List<MarketResponseDto>>> response = restTemplate.exchange(url, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<BasicResponse<List<MarketResponseDto>>>() {
                });

        if (!ObjectUtils.isEmpty(response) &&
                !ObjectUtils.isEmpty(response.getBody())
                && response.getStatusCodeValue() == 200 &&
                !ObjectUtils.isEmpty(response.getBody().getMessage())) {
            BasicResponse basicRes = response.getBody();
            marketResponseDto = response.getBody().getData();
        }
        return marketResponseDto;
    }

    @Override
    public List<BigInteger> getOrderIdForMarket(BigInteger marketId) {
        List<BigInteger> orderResponseDto = new ArrayList<BigInteger>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // httpEnitity
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        String url = addressServiceUrl + "/getOrderForMarketId?marketId=" + marketId;
        ResponseEntity<BasicResponse<List<BigInteger>>> response = restTemplate.exchange(url, HttpMethod.POST,
                requestEntity, new ParameterizedTypeReference<BasicResponse<List<BigInteger>>>() {
                });

        if (!ObjectUtils.isEmpty(response) &&
                !ObjectUtils.isEmpty(response.getBody())
                && response.getStatusCodeValue() == 200 &&
                !ObjectUtils.isEmpty(response.getBody().getMessage())) {
            BasicResponse basicRes = response.getBody();
            orderResponseDto = response.getBody().getData();
        }
        return orderResponseDto;
    }
}
