package com.ngoclbph26472.assignment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleOffDTO {
    private Long id;
    private ProductsDTO productsDTO;
    private String descriptions;
}
