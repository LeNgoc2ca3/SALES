package com.ngoclbph26472.assignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "address")
    private String address;

    @ToString.Exclude
    @JsonBackReference
//được sử dụng ở phía "sở hữu" của mối quan hệ, nơi mối quan hệ được khởi tạo><@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "accounts_id")
    private Accounts accounts;

    @JsonManagedReference
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    List<OrdersDetails> orderDetails;

}
