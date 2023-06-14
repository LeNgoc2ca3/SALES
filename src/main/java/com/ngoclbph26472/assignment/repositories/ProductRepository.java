package com.ngoclbph26472.assignment.repositories;

import com.ngoclbph26472.assignment.entity.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

//    @Query(value = "SELECT * FROM Products  WHERE name= :name or price ", nativeQuery = true)
//    List<Product> findAll(String name, Pageable pageable);
//    Page<Products> findAllByName(@Param("name") String name, Pageable pageable);


    //    @Query("SELECT p FROM Products p WHERE (p.name like :name or p.categories.name like :cateName)  AND p.price between COALESCE(:price1,0) and COALESCE(:price2,10000) ")
//    Page<Products> findAll(@Param("name") String name, @Param("cateName") String cateName, @Param("price1") BigDecimal price1 , @Param("price2") BigDecimal price2
//             , Pageable pageable);
    @Query(value = "SELECT * FROM Products p where p.name  like :name  AND p.price between COALESCE(:price1,0) and COALESCE(:price2,100000)",nativeQuery = true)
    Page<Products> findAll(@Param("name") String name, @Param("price1") BigDecimal price1, @Param("price2") BigDecimal price2
            , Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Products p SET p.quantity = p.quantity - :quantityUp WHERE p.id = :id", nativeQuery = true)
    void updateQuantity(@Param("quantityUp") Integer quantity, @Param("id") Long id);

    @Query(value = "select p from Products p where p.categories.name like :name")
    List<Products> findAllByCategoriesContaining(@Param("name") String categories);
}
