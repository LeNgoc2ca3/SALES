package com.ngoclbph26472.assignment.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_details")
public class OrdersDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

//    @ToString.Exclude
    @JsonManagedReference
//được sử dụng ở phía "sở hữu" của mối quan hệ, nơi mối quan hệ được khởi tạo><@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Products products;

    @ToString.Exclude
    @JsonManagedReference//được sử dụng ở phía "sở hữu" của mối quan hệ, nơi mối quan hệ được khởi tạo
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders orders;
}
