package com.johndeere.adzone.admin.service.impl;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.johndeere.adzone.admin.dao.UserCartDao;
import com.johndeere.adzone.admin.entity.UserCart;
import com.johndeere.adzone.admin.repository.UserCartRepository;
import com.johndeere.adzone.admin.requestdto.UserCartRequestDto;
import com.johndeere.adzone.admin.responsedto.DocumentGridResponseDto;
import com.johndeere.adzone.admin.responsedto.MyCartDetailDto;
import com.johndeere.adzone.admin.service.DocumentConnectorService;
import com.johndeere.adzone.admin.service.UserCartService;
import com.johndeere.adzone.config.MessageProperties;
import com.johndeere.adzone.responsedto.BasicResponse;
import com.johndeere.adzone.responsedto.BasicResponseUtil;
import com.johndeere.adzone.responsedto.ErrorResponse;
import com.johndeere.adzone.responsedto.SuccessResponse;
import com.johndeere.adzone.utility.MapperUtil;
import com.johndeere.adzone.utility.ObjectMapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserCartServiceImpl implements UserCartService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private MapperUtil<UserCartRequestDto, UserCart> userReqMapper = new MapperUtil<>();

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private MessageProperties messageProperties;

    @Autowired
    private DocumentConnectorService documentConnectorService;

    @SuppressWarnings("unused")
    @Override
    public BasicResponse addToCart(UserCartRequestDto userCartRequestDto) {
        BasicResponse basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        log.info("Request for Add  to Cart");

        UserCart DocumentId = userCartRepository.findByDocumentFileIdAndCreatedByAndDeleteFlag(
                userCartRequestDto.getDocumentFileId(), userCartRequestDto.getCreatedBy(), 0);
        if (DocumentId != null) {
            return BasicResponseUtil.setBasicResponse(null, null, messageProperties.getOrderExistSuccessCode(),
                    messageProperties.getOrderExistSuccessMessage(), null);
        }

        UserCart userCart = userReqMapper.transfer(userCartRequestDto, UserCart.class);

        userCart.setCreatedDate(LocalDateTime.now());
        userCart.setDeleteFlag(0);
        userCart.setDocumentFileId(userCartRequestDto.getDocumentFileId());

        try {
            log.info("OrderService --> add to cart ");

            UserCart MyCart = userCartRepository.save(userCart); // AddToCart Repo

            if (MyCart != null) {

                message.setMessageCode(messageProperties.getOrderAddToCartSuccessCode());
                message.setMessageDescription(messageProperties.getOrderAddToCartSuccessMessage());
                basicResponse.setData(userCart);
                basicResponse.setMessage(message);
                log.info("OrderService --> addToCart -->   Item Added saved successfully !" + userCart);
            } else {
                error.setErroCode("-1");
                error.setErrorMessage("AddToCart could not be placed !");
                log.error("OrderService --> addToCart -- addToCart could not be created ! !");
                basicResponse.setError(error);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error.setErroCode("-1");
            error.setErrorMessage("Item could not be added !");
            log.error("OrderService --> addOrders " + e.getMessage());
            basicResponse.setError(error);
        }
        log.info("ABCD::::::" + basicResponse);
        return basicResponse;
    }

    @Override
    public BasicResponse<Page<MyCartDetailDto>> getMyCart(String racfId, Pageable pageable) {
        BasicResponse<Page<MyCartDetailDto>> basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        Page<MyCartDetailDto> myCartDetailDtoPage = null;
        Page<UserCart> userCart = userCartRepository.getProduct(racfId, pageable);

        try {
            if (!userCart.isEmpty()) {
                myCartDetailDtoPage = ObjectMapperUtils.mapEntityPageIntoDtoPage(userCart, MyCartDetailDto.class);

                Iterator orderItr = myCartDetailDtoPage.iterator();
                while (orderItr.hasNext()) {
                    MyCartDetailDto myCartDetailDto = (MyCartDetailDto) orderItr.next();

                    if (myCartDetailDto.getDocumentFileId() != null) {

                        DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                                .getDocumentFileById(myCartDetailDto.getDocumentFileId());

                        myCartDetailDto.setDocumentName(documentGridResponseDto.getDocumentFileName());
                        myCartDetailDto.setDocumentFileType(documentGridResponseDto.getExtension());
                        myCartDetailDto.setDocumentFileSize(documentGridResponseDto.getDocumentFileSize());
                        myCartDetailDto.setThumbnailUrl(documentGridResponseDto.getThumbnailUrl());
                        myCartDetailDto.setDocumentFileName(documentGridResponseDto.getDocumentFileName());
                        myCartDetailDto.setCreatedDate(documentGridResponseDto.getDocumentCreatedDate());
                        myCartDetailDto.setUpdatedDate(documentGridResponseDto.getDocumentUpdatedDate());
                        myCartDetailDto.setWishList(documentGridResponseDto.isWishList());
                        if (documentGridResponseDto.getPrice() != null)
                            myCartDetailDto.setPrice(documentGridResponseDto.getPrice());
                        if (documentGridResponseDto.getCurrency() != null)
                            myCartDetailDto.setCurrency(documentGridResponseDto.getCurrency());
                    }
                }
                message = new SuccessResponse(messageProperties.getOrderListedProductSuccessCode(),
                        messageProperties.getOrderListedProductSuccessMessage());
                log.info(
                        "OrderManagmentService --> getMyCart :  List of  My order from cart for printable Material  is generated ");
            } else {
                error = new ErrorResponse("-1",
                        "There is no Order in cart ");
                basicResponse.setError(error);
                log.info("OrderService --> getMyCart : There is no Order in cart for Printable material  ");
                return basicResponse;
            }
            basicResponse.setMessage(message);
            basicResponse.setData(myCartDetailDtoPage);
            return basicResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("OrderService --> getMyOrder " + ex.getMessage());
        }
        return basicResponse;
    }

    @Override
    public BasicResponse<Page<MyCartDetailDto>> getMyCartDoc(String racfId, Pageable pageable) {
        BasicResponse<Page<MyCartDetailDto>> basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        Page<MyCartDetailDto> myCartDetailDtoPage = null;
        Page<UserCart> userCart = userCartRepository.getDocument(racfId, pageable);

        try {
            if (!userCart.isEmpty()) {
                myCartDetailDtoPage = ObjectMapperUtils.mapEntityPageIntoDtoPage(userCart, MyCartDetailDto.class);

                Iterator orderItr = myCartDetailDtoPage.iterator();
                while (orderItr.hasNext()) {
                    MyCartDetailDto myCartDetailDto = (MyCartDetailDto) orderItr.next();

                    if (myCartDetailDto.getDocumentFileId() != null) {

                        DocumentGridResponseDto documentGridResponseDto = documentConnectorService
                                .getDocumentFileById(myCartDetailDto.getDocumentFileId());

                        myCartDetailDto.setDocumentName(documentGridResponseDto.getDocumentName());
                        myCartDetailDto.setDocumentFileType(documentGridResponseDto.getExtension());
                        myCartDetailDto.setDocumentFileSize(documentGridResponseDto.getDocumentFileSize());
                        myCartDetailDto.setThumbnailUrl(documentGridResponseDto.getThumbnailUrl());
                        myCartDetailDto.setDocumentFileName(documentGridResponseDto.getDocumentFileName());
                        myCartDetailDto.setCreatedDate(documentGridResponseDto.getDocumentCreatedDate());
                        myCartDetailDto.setUpdatedDate(documentGridResponseDto.getDocumentUpdatedDate());
                        myCartDetailDto.setWishList(documentGridResponseDto.isWishList());
                        if (documentGridResponseDto.getPrice() != null)
                            myCartDetailDto.setPrice(documentGridResponseDto.getPrice());
                        if (documentGridResponseDto.getCurrency() != null)
                            myCartDetailDto.setCurrency(documentGridResponseDto.getCurrency());
                    }
                }

                message = new SuccessResponse(messageProperties.getOrderListedDocumentSuccessCode(),
                        messageProperties.getOrderListedDocumentSuccessMessage());
                log.info(
                        "OrderManagmentService --> getMyCart :  List of  My order from cart  for Document is generated ");
            } else {
                error = new ErrorResponse("-1",
                        "There is no Order in cart ");
                basicResponse.setError(error);
                log.info("OrderService --> getMyCart : There is no Order in cart for Document  ");
                return basicResponse;
            }
            basicResponse.setMessage(message);
            basicResponse.setData(myCartDetailDtoPage);
            return basicResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("OrderService --> getMyOrder " + ex.getMessage());
        }
        return basicResponse;

    }

    @Override
    public BasicResponse removeMyCart(List<UserCartRequestDto> userCartRequestDto) {
        List<UserCart> userCartRemoveList = new ArrayList<>();
        UserCart userCart = new UserCart();
        BasicResponse basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);
        log.info("Request for Remove  from Cart");

        for (UserCartRequestDto ucrd : userCartRequestDto) {

            userCart = userReqMapper.transfer(ucrd, UserCart.class);

            try {

                log.info("remove My cart  --> deleteCart");

                if (userCart != null) {
                    Optional<UserCart> ucr = userCartRepository.findById(userCart.getUserCartId());
                    if (ucr.isPresent()) {
                        userCart.setDeleteFlag(1);
                        userCart.setCreatedDate(ucr.get().getCreatedDate());
                        userCart.setCreatedBy(ucr.get().getCreatedBy());
                        userCart.setUpdatedBy(ucr.get().getCreatedBy());
                        userCart.setUpdatedDate(LocalDateTime.now());
                        userCart.setDocumentFileId(ucr.get().getDocumentFileId());
                        userCart.setDocumentId(ucr.get().getDocumentId());

                        Object update = userCartRepository.save(userCart);

                        if (update != null) {
                            userCartRemoveList.add(userCart);
                            message.setMessageCode(messageProperties.getOrderRemoveSuccessCode());
                            message.setMessageDescription(messageProperties.getOrderRemoveSuccessMessage());
                            basicResponse.setData(userCartRemoveList);
                            basicResponse.setMessage(message);
                            log.info("OrderService --> removeOrder -- remove deleted successfully !");
                        } else {
                            error.setErroCode("-1");
                            error.setErrorMessage("order could not be removed !");
                            log.error("OrderService --> removeOrder -- remove  Order from cart could not deleted ! !");
                            basicResponse.setError(error);
                        }
                    } else {
                        error.setErroCode("-1");
                        error.setErrorMessage("order could not be removed !");
                        log.error("OrderService --> removeOrder --  remove  Order from cart could not  deleted ! !");
                        basicResponse.setError(error);
                    }
                }

            } catch (Exception e) {
                error.setErroCode("-1");
                error.setErrorMessage("order could not be removed !");
                log.error("OrderService --> removeOrder " + e.getMessage());
                basicResponse.setError(error);
            }
        }
        return basicResponse;

    }

    @Override
    public BasicResponse moveToWishList(List<BigInteger> cartIdsList) {

        BasicResponse basicResponse = new BasicResponse();
        ErrorResponse error = new ErrorResponse(null, null, null);
        SuccessResponse message = new SuccessResponse(null, null);

        try {
            List<UserCart> userCartList = userCartRepository.findAllById(cartIdsList);

            if (!userCartList.isEmpty()) {
                for (UserCart userCart : userCartList) {
                    BigInteger documentId = userCart.getDocumentId();
                    String racfId = userCart.getCreatedBy();
                    BigInteger documentFileId = userCart.getDocumentFileId();
                    if (documentConnectorService.addWishList(documentId, racfId, documentFileId,
                            userCart.getProductFlag())) {
                        userCartRepository.deleteById(userCart.getUserCartId());
                    }

                }
                message.setMessageCode("10002");
                message.setMessageDescription("Item moved from cart to  wishList");
                basicResponse.setMessage(message);
            }
        } catch (Exception ex) {
            error.setErroCode("-1");
            error.setErrorMessage("Item not moved to wishlist");
            basicResponse.setError(error);
        }
        return basicResponse;

    }

}
