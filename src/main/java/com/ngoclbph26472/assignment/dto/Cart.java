package com.ngoclbph26472.assignment.dto;

import com.ngoclbph26472.assignment.entity.Products;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Cart {

//    Map<Products, Integer> dsGioHang = new HashMap<>();

    Map<Long, Products> dsGioHang = new HashMap<>();

//    Map<Long, Integer> dsGioHang = new HashMap<>();
}
