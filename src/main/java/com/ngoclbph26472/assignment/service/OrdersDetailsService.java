package com.ngoclbph26472.assignment.service;

import com.ngoclbph26472.assignment.dto.OrdersDetailsDTO;
import com.ngoclbph26472.assignment.entity.OrdersDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface OrdersDetailsService {

    ResponseEntity<List<OrdersDetailsDTO>> getAll();

    void generateExcel(HttpServletResponse response) throws IOException;

    OrdersDetails create(OrdersDetails ordersDetails);

    List<OrdersDetails> create(List<OrdersDetails> ordersDetails);
}
