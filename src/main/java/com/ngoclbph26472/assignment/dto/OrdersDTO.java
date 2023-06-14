package com.ngoclbph26472.assignment.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersDTO {
    private Long id;
    private AccountsDTO accountsDTO;
    private LocalDate createDate;
    private String address;
}
