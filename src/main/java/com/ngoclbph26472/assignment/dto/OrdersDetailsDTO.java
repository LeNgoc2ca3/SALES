package com.ngoclbph26472.assignment.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersDetailsDTO {
    private Long id;
    private OrdersDTO ordersDTO;
    private ProductsDTO productsDTO;
    private BigDecimal price;
    private Integer quantity;
}
