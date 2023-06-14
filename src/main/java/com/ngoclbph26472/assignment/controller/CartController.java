package com.ngoclbph26472.assignment.controller;

import com.ngoclbph26472.assignment.dto.Cart;
import com.ngoclbph26472.assignment.entity.Categories;
import com.ngoclbph26472.assignment.entity.Products;
import com.ngoclbph26472.assignment.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assignment/cart")
@SessionScope
public class CartController {

    private int number = 0;

    @Autowired
    ProductsService service;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    CartService cartService;

    @Autowired
    OrdersService ordersService;

    @GetMapping("/add")
    public String themSanPhamVaoGio(@ModelAttribute("productDetail1") Products products,Model model) {
//        number = quantity;
//        products.setQuantity(quantity);
//        for (Products cart: service.findAllByCategory("")) {
//            if(products.getQuantity() > cart.){
//                model.addAttribute("loi","Vui lòng nhập trong khoảng cho phép");
//                model.addAttribute("listCate", categoriesService.getAll());
//                model.addAttribute("productDetail1", products);
//                return "cart/detail";
//            }
//        }
        cartService.themSanPham(products.getId(), products);
        return "redirect:/assignment/cart/view-cart";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Products products, Model model) {
        model.addAttribute("listCate", categoriesService.getAll());
        model.addAttribute("productDetail1", products);
        return "cart/detail";
    }

    @GetMapping("/view-cart")
    public String xemGioHang(Model model) {
        Map<Long, Products> sanPhamTrongGio = cartService.xemSanPham();
        BigDecimal total = BigDecimal.valueOf(0);
        for (Map.Entry<Long, Products> longProductsMap : cartService.xemSanPham().entrySet()) {
            total = longProductsMap.getValue().getPrice().multiply(BigDecimal.valueOf(longProductsMap.getValue().getQuantity())).add(total);
        }
        model.addAttribute("total", total);
        model.addAttribute("sanPhamTrongGio", sanPhamTrongGio);
        return "cart/cart";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        cartService.delete(id);
        return "redirect:/assignment/cart/view-cart";
    }

    @GetMapping("/delete")
    public String clear() {
        cartService.deleteAll();
        return "redirect:/assignment/cart/view-cart";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/pay")
    public String pay(@RequestParam("address") String address, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        ordersService.create(request, address);
        cartService.deleteAll();
        redirectAttributes.addAttribute("success", "True");
        return "redirect:/assignment/cart";
    }

    @GetMapping
    public String cartProduct(Model model,@RequestParam(value = "categories", defaultValue = "") String categories) {
        model.addAttribute("items", service.findAllByCategory(categories));
        return "cart/listCart";
    }
}
