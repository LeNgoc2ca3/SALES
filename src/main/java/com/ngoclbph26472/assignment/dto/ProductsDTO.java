package com.ngoclbph26472.assignment.dto;

import com.ngoclbph26472.assignment.entity.Products;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductsDTO {

    private Long id;

    @NotEmpty(message = "Không được để trống tên")
    private String name;

    @NotEmpty(message = "Không được để trống ảnh")
    private String image;

    @NotNull(message = "Không được để trống giá")
    @DecimalMin(value = "0", message = "Giá không được âm")
    @DecimalMax(value = "1000", message = "Giá không được vượt quá 1000")
    private BigDecimal price;

    private LocalDate createDate;

    @NotNull(message = "Không được để trống số lượng")
    @Min(value = 1,message = "Số lượng phải trên 0 khi thêm mới")
    @Max(value = 1000, message = "Số lượng không quá 1000")
//    @Pattern(regexp = ".*\\D.*",message = "Vui lòng nhập số")
    private Integer quantity;

    private Byte available;

    private CategoriesDTO categoriesDTO;
}
