package com.ngoclbph26472.assignment.service.ipml;

import com.ngoclbph26472.assignment.Untils.PageableUntils;
import com.ngoclbph26472.assignment.dto.ProductsDTO;
import com.ngoclbph26472.assignment.entity.Products;
import com.ngoclbph26472.assignment.repositories.OrderDetailRepository;
import com.ngoclbph26472.assignment.repositories.ProductRepository;
import com.ngoclbph26472.assignment.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductsIpml implements ProductsService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    ProductRepository repository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<Products> getAll() {
        List<Products> products = repository.findAll();
        return products;
    }

    @Override
    public List<ProductsDTO> getAll1() {
        List<Products> listProduct = repository.findAll();
        TypeToken<List<ProductsDTO>> typeToken = new TypeToken<>() {
        };
        List<ProductsDTO> listProductDTO = mapper.map(listProduct, typeToken.getType());
        return listProductDTO;
    }

    @Override
    public Page<Products> findAll(String name, Double price1, Double price2, int pageIndex, int pageSize) {
        Pageable pageable = PageableUntils.pageableUtils( pageIndex, pageSize);
        Page<Products> list = repository.findAll("%" + name + "%",
                BigDecimal.valueOf(price1),BigDecimal.valueOf(price2), pageable);
        return list;
    }

    @Override
    public Products create(ProductsDTO productsDTO) {
        Products products = mapper.map(productsDTO, Products.class);
        products.setCreateDate(LocalDate.now());
        products.setAvailable((byte) 1);
        return repository.save(products);
    }

    @Override
    public Products update(Products products) {
        if (products.getQuantity() == 0) {
            products.setCreateDate(LocalDate.now());
            products.setAvailable((byte) 0);
            return repository.save(products);
        }
        products.setAvailable((byte) 1);
        products.setCreateDate(LocalDate.now());
        return repository.save(products);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteByProductsId(id);
        repository.deleteById(Math.toIntExact(id));
    }

    @Override
    public void updateQuantity(Long id,Integer sl) {
        repository.updateQuantity(sl,id);
    }

    @Override
    public List<Products> findAllByCategory(String categories) {
        return repository.findAllByCategoriesContaining("%" + categories + "%");
    }
}
