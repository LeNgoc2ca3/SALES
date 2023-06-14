package com.ngoclbph26472.assignment.restcontroller;

import com.ngoclbph26472.assignment.dto.CategoriesDTO;
import com.ngoclbph26472.assignment.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assignment/category")
public class CategoryController {

    @Autowired
    private CategoriesService service;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CategoriesDTO>> getAll(){ ;
        return ResponseEntity.ok(service.getAll());
    }
}
