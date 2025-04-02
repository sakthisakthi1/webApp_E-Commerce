package com.johndeere.adzone.admin.repository;

import java.math.BigInteger;
import java.util.Optional;

import com.johndeere.adzone.admin.entity.UserCart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, BigInteger> {

    @Query(value="select o.* from \"order\".user_cart o where o.created_by like concat('%',?1,'%') and o.delete_flag = 0 and o.product_flag = 1 ",nativeQuery=true)
     Page<UserCart> getProduct(String racfId, Pageable pageable);

    UserCart findByDocumentIdAndCreatedByAndDeleteFlag(BigInteger documentId, String createdBy, int i);

    @Query(value="select o.* from \"order\".user_cart o where o.created_by like concat('%',?1,'%') and o.delete_flag = 0 and o.product_flag = 0",nativeQuery=true)
    Page<UserCart> getDocument(String racfId, Pageable pageable);

    Optional<UserCart> findByDocumentFileIdAndDeleteFlagAndCreatedBy(BigInteger id, int i, String racfId);

	UserCart findByDocumentFileIdAndCreatedByAndDeleteFlag(BigInteger documentId, String createdBy, int i);

  
    
   

  

   
  


    
}
