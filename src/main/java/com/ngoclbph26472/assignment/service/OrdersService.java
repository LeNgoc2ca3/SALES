package com.ngoclbph26472.assignment.service;

import com.ngoclbph26472.assignment.domail.ReportChartLow;
import com.ngoclbph26472.assignment.domail.ReportChartTop;
import com.ngoclbph26472.assignment.entity.Orders;
import com.ngoclbph26472.assignment.entity.OrdersDetails;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public interface OrdersService {

    List<Orders> getAll(String username);

    List<OrdersDetails> findById(long id);

//    List<Orders> findById(long id);
    List<ReportChartTop> findAllTop(LocalDate date);

    List<ReportChartLow> findAllLow();

    Orders create(HttpServletRequest request, String address);
}
