package com.ngoclbph26472.assignment.repositories;

import com.ngoclbph26472.assignment.domail.ReportChartLow;
import com.ngoclbph26472.assignment.domail.ReportChartTop;
import com.ngoclbph26472.assignment.entity.OrdersDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrdersDetails, Long> {

    @Query("select o from OrdersDetails o where o.orders.id=:id")
    List<OrdersDetails> findAllId(@Param("id") Long id);

    @Query("""
            select new com.ngoclbph26472.assignment.domail.ReportChartTop(o.products.name,sum(o.quantity)) from OrdersDetails o where (:createDate is null or o.orders.createDate = :createDate) group by o.products.name order by sum(o.quantity) desc limit 10
            """)
    List<ReportChartTop> chartTop(@Param("createDate") LocalDate date);

    @Query("""
            select new com.ngoclbph26472.assignment.domail.ReportChartLow(p.name as name,p.quantity as quantity) from Products p left join OrdersDetails o on p.id = o.products where o.quantity is null order by p.quantity limit 10
            """)
    List<ReportChartLow> chartLow();

    @Transactional
    @Modifying
    void deleteByProductsId(Long id);

}
