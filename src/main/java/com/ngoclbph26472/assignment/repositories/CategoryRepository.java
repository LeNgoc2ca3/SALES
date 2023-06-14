package com.ngoclbph26472.assignment.repositories;

import com.ngoclbph26472.assignment.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Integer> {
}
