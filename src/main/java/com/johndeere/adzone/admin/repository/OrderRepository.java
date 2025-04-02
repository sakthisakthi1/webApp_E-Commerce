package com.johndeere.adzone.admin.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johndeere.adzone.admin.entity.Order;
import com.johndeere.adzone.admin.responsedto.OrderResponseDto;

@Repository
public interface OrderRepository extends JpaRepository<Order, BigInteger> {

    @Query(value = "select o.* from \"order\".m_jd_orders o where o.racfid like concat('%',?1,'%') and o.delete_flag = 0", nativeQuery = true)
    Page<Order> findByRacfId(String racfId, Pageable pageable);

    @Query(value = "select o.* from \"order\".m_jd_orders o ", nativeQuery = true)
    Page<Order> getAll(Pageable pageable);

    Order findByOrderId(BigInteger orderId);

    @Query(value = "select o.* from \"order\".m_jd_orders o where o.order_id=?1", nativeQuery = true)
    Order getOrder(BigInteger orderId);

    @Query(value = "select o.* from \"order\".m_jd_orders o where o.address_id in (?1) and o.delete_flag = 0", nativeQuery = true)
    List<Order> getAddressId(BigInteger addressId);

    @Query(value = "select o.* from \"order\".m_jd_orders o where o.address_id in (?1)", nativeQuery = true)
    List<OrderResponseDto> getForAddressId(BigInteger[] addressIdarray);

}