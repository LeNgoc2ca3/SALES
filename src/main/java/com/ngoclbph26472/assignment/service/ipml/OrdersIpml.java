package com.ngoclbph26472.assignment.service.ipml;

import com.ngoclbph26472.assignment.domail.ReportChartLow;
import com.ngoclbph26472.assignment.domail.ReportChartTop;
import com.ngoclbph26472.assignment.entity.Accounts;
import com.ngoclbph26472.assignment.entity.Orders;
import com.ngoclbph26472.assignment.entity.OrdersDetails;
import com.ngoclbph26472.assignment.entity.Products;
import com.ngoclbph26472.assignment.repositories.AccountRepository;
import com.ngoclbph26472.assignment.repositories.OrderDetailRepository;
import com.ngoclbph26472.assignment.repositories.OrderRepository;
import com.ngoclbph26472.assignment.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrdersIpml implements OrdersService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductsService service;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrdersDetailsService ordersDetailsService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Orders> getAll(String username) {
        return orderRepository.findByUsername(username);
    }

    @Override
    public List<OrdersDetails> findById(long id) {
        System.out.println(orderDetailRepository.findAllId(id));
        return orderDetailRepository.findAllId(id);
    }

    @Override
    public List<ReportChartTop> findAllTop(LocalDate date) {
//        System.out.println(orderDetailRepository.chartTop());
        return orderDetailRepository.chartTop( date );
    }

    @Override
    public List<ReportChartLow> findAllLow() {
        System.out.println(orderDetailRepository.chartLow());
        return orderDetailRepository.chartLow();
    }

    @Override
    public Orders create(HttpServletRequest request, String address) {
        Principal principal = request.getUserPrincipal();
        Optional<Accounts> account = accountRepository.findByUsername(principal.getName());
        Orders orders = new Orders();
        orders.setAddress(address);
        orders.setAccounts(account.get());
        orders.setCreateDate(LocalDate.now());
        orderRepository.save(orders);
        OrdersDetails details = new OrdersDetails();
        for (Map.Entry<Long, Products> longProductsMap : cartService.xemSanPham().entrySet()) {
            service.updateQuantity(longProductsMap.getKey(), longProductsMap.getValue().getQuantity());
            details.setId(0L);
            details.setPrice(longProductsMap.getValue().getPrice().multiply(BigDecimal.valueOf(longProductsMap.getValue().getQuantity())));
            details.setQuantity(longProductsMap.getValue().getQuantity());
            details.setProducts(longProductsMap.getValue());
            details.setOrders(orders);
            ordersDetailsService.create(details);
        }
        return orders;
    }
}
