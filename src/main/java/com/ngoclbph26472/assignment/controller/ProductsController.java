package com.ngoclbph26472.assignment.controller;

import com.ngoclbph26472.assignment.dto.ProductsDTO;

import com.ngoclbph26472.assignment.entity.Products;
import com.ngoclbph26472.assignment.repositories.CategoryRepository;
import com.ngoclbph26472.assignment.repositories.ProductRepository;
import com.ngoclbph26472.assignment.service.CategoriesService;
import com.ngoclbph26472.assignment.service.ProductsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/assignment/product")
public class ProductsController {

    private String img = "";

    @Autowired
    ProductsService productsService;

    @Autowired
    ProductRepository repository;

    @Autowired
    CategoriesService categoriesService;

    @GetMapping
    public String index(@RequestParam(name = "page", defaultValue = "0") Integer pageIndex,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        @RequestParam(value = "price1",required = false) Double price11,
                        @RequestParam(value = "price2",required = false) Double price22,
                        @RequestParam(name = "nameProduct", defaultValue = "") String name, Model model) {

        if (pageIndex < 0) pageIndex = 0;
//        price11 = null;
//        price22 = null;
        if(price11 == null && price22 == null){
            model.addAttribute("product", new ProductsDTO());
            model.addAttribute("listCate", categoriesService.getAll());
            model.addAttribute("listProduct", productsService.findAll(name, 0.0, 900000.0, pageIndex, pageSize));
            model.addAttribute("price1","");
            model.addAttribute("price2","");
            model.addAttribute("nameProduct",name);
            return "product/productInput";
        } else if (price11 != null && price22 == null){
            model.addAttribute("product", new ProductsDTO());
            model.addAttribute("listCate", categoriesService.getAll());
            model.addAttribute("listProduct", productsService.findAll(name, price11, 900000.0, pageIndex, pageSize));
            model.addAttribute("price1",price11);
            model.addAttribute("price2","");
            model.addAttribute("nameProduct",name);
            return "product/productInput";
        } else if (price11 == null && price22 != null){
            model.addAttribute("product", new ProductsDTO());
            model.addAttribute("listCate", categoriesService.getAll());
            model.addAttribute("listProduct", productsService.findAll(name, 0.0, price22, pageIndex, pageSize));
            model.addAttribute("price1","");
            model.addAttribute("price2",price22);
            model.addAttribute("nameProduct",name);
            return "product/productInput";
        }
        model.addAttribute("product", new ProductsDTO());
        model.addAttribute("listCate", categoriesService.getAll());
        model.addAttribute("listProduct", productsService.findAll(name, price11, price22, pageIndex, pageSize));
        model.addAttribute("price1", price11);
        model.addAttribute("price2", price22);
        model.addAttribute("nameProduct", name);
        return "product/productInput";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("listCate", categoriesService.getAll());
        model.addAttribute("productAdd", new ProductsDTO());
        return "product/productAdd";
    }

    @PostMapping("")
    public String create(@Valid @ModelAttribute("productAdd") ProductsDTO productsDTO, Errors errors, RedirectAttributes redirectAttributes
            , Model model) {
        if (errors.hasErrors()) {
            for (Products products : productsService.getAll()) {
                if (products.getName().equalsIgnoreCase(productsDTO.getName())) {
                    errors.rejectValue("name","ProductsDTO","Tên sản phẩm đã tồn tại");
                }
            }
            model.addAttribute("listCate", categoriesService.getAll());
            model.addAttribute("productAdd", productsDTO);
            return "product/productAdd";
        }
        productsService.create(productsDTO);
        redirectAttributes.addAttribute("success", "True");
        return "redirect:/assignment/product";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Products products, Model model) {
        model.addAttribute("listCate", categoriesService.getAll());
        model.addAttribute("productDetail", products);
        return "product/productDetail";
    }

    @GetMapping("/update/{id}")
    public String detailUpdate(@PathVariable("id") Products products, Model model) {
        img = products.getImage();
        model.addAttribute("listCate", categoriesService.getAll());
        model.addAttribute("productUpdate", products);
        return "product/productUpdate";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("productUpdate") Products products, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("productUpdate", products);
            model.addAttribute("listCate", categoriesService.getAll());
            return "product/productUpdate";
        }
        if (!products.getImage().isBlank()) {
            products.setImage(products.getImage());
            productsService.update(products);
            redirectAttributes.addAttribute("success", "True");
            return "redirect:/assignment/product";
        }
        products.setImage(img);
        redirectAttributes.addAttribute("success", "True");
        productsService.update(products);
        return "redirect:/assignment/product";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        productsService.delete(Long.valueOf(id));
        redirectAttributes.addAttribute("success", "True");
        return "redirect:/assignment/product";
    }
}
