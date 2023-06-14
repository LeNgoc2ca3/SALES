package com.ngoclbph26472.assignment.restcontroller;

import com.ngoclbph26472.assignment.dto.ProductsDTO;
import com.ngoclbph26472.assignment.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assignment/product/ngoc")
public class ProductRestController {

    @Autowired
    private ProductsService service;

    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ProductsDTO>> getAll(){ ;
        System.out.println();
        return ResponseEntity.ok(service.getAll1());
    }
}
