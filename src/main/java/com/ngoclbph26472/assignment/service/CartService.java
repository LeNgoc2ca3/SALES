package com.ngoclbph26472.assignment.service;

import com.ngoclbph26472.assignment.entity.Products;

import java.util.Map;

public interface CartService {

//    void themSanPham(Products products, Integer soluong);
//
//    Map<Products, Integer> xemSanPham();

    void themSanPham(Long id, Products products);

    Map<Long, Products> xemSanPham();

    void delete(Long id);

    void deleteAll();
}
