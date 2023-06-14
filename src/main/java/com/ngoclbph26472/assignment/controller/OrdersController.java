package com.ngoclbph26472.assignment.controller;

import com.ngoclbph26472.assignment.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/assignment/order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping()
    public String getAll(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        model.addAttribute("orders", ordersService.getAll(principal.getName()));
        return "order/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")Long id, Model model){
//        System.out.println(id);
        model.addAttribute("details", ordersService.findById(id));
        return "order/detail";
    }
}
