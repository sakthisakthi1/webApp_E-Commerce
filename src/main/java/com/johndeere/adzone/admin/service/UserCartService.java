package com.johndeere.adzone.admin.service;

import java.math.BigInteger;
import java.util.List;

import com.johndeere.adzone.admin.requestdto.UserCartRequestDto;
import com.johndeere.adzone.admin.responsedto.MyCartDetailDto;
import com.johndeere.adzone.responsedto.BasicResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserCartService {

    BasicResponse addToCart(UserCartRequestDto userCartRequestDto);

    BasicResponse removeMyCart(List<UserCartRequestDto> userCartRequestDto);

    BasicResponse moveToWishList(List<BigInteger> cartIdsList);

    BasicResponse<Page<MyCartDetailDto>> getMyCart(String racfId, Pageable pageable);

    BasicResponse<Page<MyCartDetailDto>> getMyCartDoc(String racfId, Pageable pageable);
}
