package com.ngoclbph26472.assignment.service;

import com.ngoclbph26472.assignment.dto.ProductsDTO;
import com.ngoclbph26472.assignment.entity.Products;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductsService {

    List<Products> getAll();

    List<ProductsDTO> getAll1();

    Page<Products> findAll(String name, Double price1, Double price2, int pageIndex, int pageSize);

    Products create(ProductsDTO productsDTO);

    Products update(Products products);

    void delete(Long id);

    void updateQuantity(Long id, Integer sl);

    List<Products> findAllByCategory(String categories);

}
