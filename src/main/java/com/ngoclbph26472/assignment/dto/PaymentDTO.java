package com.ngoclbph26472.assignment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDTO {
    private Long id;
    private AccountsDTO accountsId;
    private String name;
    private String descriptions;
}
