package com.johndeere.adzone.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

import com.johndeere.adzone.admin.requestdto.UserCartRequestDto;
import com.johndeere.adzone.admin.responsedto.MyCartDetailDto;
import com.johndeere.adzone.admin.service.UserCartService;
import com.johndeere.adzone.responsedto.BasicResponse;

@RestController
@RequestMapping("v1/api/OrderManagementService")
public class UserCartController {
    
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private UserCartService  userCartService;

    @PostMapping("/addToCart")
    public ResponseEntity<BasicResponse> addToCart(@Validated @RequestBody UserCartRequestDto userCartRequestDto ){
		BasicResponse result = userCartService.addToCart(userCartRequestDto);
		log.info("OrderManagmentService --> /addOrders "+result);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/MyCart/{racfId}")
	public ResponseEntity<BasicResponse<Page<MyCartDetailDto>>> getMyCart(@RequestParam(required = false, defaultValue = "0") int page,

			@RequestParam(required = false, defaultValue = "10") int size,

			@RequestParam(required = false, defaultValue = "desc") String sort,

			@RequestParam(required = false, defaultValue = "created_date") String sortBy,
           
            @PathVariable String racfId) {
 
			 System.out.println("RacfID:::::" + racfId);
		Pageable pageable = PageRequest.of(page, size,
				Sort.by(sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));
		
		BasicResponse<Page<MyCartDetailDto>> result = null;
        result = userCartService.getMyCart(racfId, pageable);

        log.info("OrderManagmentService --> /getMyCart");
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/MyCart/Document/{racfId}")
	public ResponseEntity<BasicResponse<Page<MyCartDetailDto>>> getMyCartDoc(@RequestParam(required = false, defaultValue = "0") int page,

			@RequestParam(required = false, defaultValue = "10") int size,

			@RequestParam(required = false, defaultValue = "desc") String sort,

			@RequestParam(required = false, defaultValue = "created_date") String sortBy,
           
            @PathVariable String racfId) {
 
			 System.out.println("RacfID:::::" + racfId);
		Pageable pageable = PageRequest.of(page, size,
				Sort.by(sort.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));
		
		BasicResponse<Page<MyCartDetailDto>> result = null;
        result = userCartService.getMyCartDoc(racfId, pageable);

        log.info("OrderManagmentService --> /getMyCartDoc");
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/removeMyCart")
	public ResponseEntity<BasicResponse> removeMyCart(@Validated @RequestBody List<UserCartRequestDto> userCartRequestDto ){
		BasicResponse result = userCartService.removeMyCart(userCartRequestDto);
		log.info("OrderManagmentService --> /addOrders "+result);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/movetowishlist")
	public ResponseEntity<BasicResponse> moveToWishList(@RequestParam List<BigInteger> cartIdsList){
		BasicResponse result = userCartService.moveToWishList(cartIdsList);
		return ResponseEntity.ok().body(result);
	}


}
