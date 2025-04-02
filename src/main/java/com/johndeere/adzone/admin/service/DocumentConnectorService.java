package com.johndeere.adzone.admin.service;

import java.math.BigInteger;
import java.util.List;

import com.johndeere.adzone.admin.responsedto.DocumentGridResponseDto;
import com.johndeere.adzone.admin.responsedto.MarketResponseDto;
import com.johndeere.adzone.admin.responsedto.OrderAddressResponseDto;
import com.johndeere.adzone.admin.responsedto.ProductResponseDto;

import org.springframework.stereotype.Service;

@Service
public interface DocumentConnectorService {

    DocumentGridResponseDto getDocumentFile(BigInteger docfileId);

    boolean addWishList(BigInteger documentId, String racfId, BigInteger documentFileId, Integer productFlag);

    DocumentGridResponseDto getDocumentFileById(BigInteger documentFileId);

    ProductResponseDto getProductDescripition(BigInteger documentId);

    OrderAddressResponseDto getAddressForId(BigInteger addressId);

    List<MarketResponseDto> getMarketForUserId(BigInteger userId);

    List<BigInteger> getOrderIdForMarket(BigInteger marketId);

}
