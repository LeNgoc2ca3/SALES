package com.ngoclbph26472.assignment.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Không được để trống tên")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Không được để trống ảnh")
    @Column(name = "image")
    private String image;

    @NotNull(message = "Không được để trống giá")
    @DecimalMin(value = "0", message = "Giá không được âm")
    @DecimalMax(value = "1000", message = "Giá không được vượt quá 1000")
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "create_date")
    private LocalDate createDate;

    @NotNull(message = "Không được để trống số lượng")
    @Min(value = 0,message = "Số lượng không được âm")
    @Max(value = 1000, message = "Số lượng không quá 1000")
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "available")
    private Byte available;

//    @JsonManagedReference
//được sử dụng ở phía "sở hữu" của mối quan hệ, nơi mối quan hệ được khởi tạo><@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Categories categories;

    @ToString.Exclude
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    private List<OrdersDetails> ordersDetails;
}
