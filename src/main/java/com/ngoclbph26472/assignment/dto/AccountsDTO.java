package com.ngoclbph26472.assignment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountsDTO {
    private Long id;

    @NotEmpty(message = "Không được để trống")
    private String username;

    @NotEmpty(message = "Không được để trống")
    private String fullname;

    @NotEmpty(message = "Không được để trống")
    private String password;

    @NotEmpty(message = "Không được để trống")
    @Email(message = "Emai không đúng định dạng")
    private String email;
    private String photo;
    private Byte actived;
    private String role;
}
