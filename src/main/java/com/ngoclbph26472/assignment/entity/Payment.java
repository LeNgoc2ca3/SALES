package com.ngoclbph26472.assignment.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @JsonManagedReference
//được sử dụng ở phía "sở hữu" của mối quan hệ, nơi mối quan hệ được khởi tạo><@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accounts_id", referencedColumnName = "id")
    private Accounts accounts;

}
