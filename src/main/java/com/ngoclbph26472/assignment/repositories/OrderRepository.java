package com.ngoclbph26472.assignment.repositories;

import com.ngoclbph26472.assignment.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o where o.accounts.username=?1")
    List<Orders> findByUsername(String username);


//    @Query(value = "select * from Orders o join OrdersDetails od on o.id = od.order_id where o.id=:id",nativeQuery = true)
//    List<Orders> findAllId(@Param("id") Long id);
}
