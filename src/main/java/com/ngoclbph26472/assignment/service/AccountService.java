package com.ngoclbph26472.assignment.service;

import com.ngoclbph26472.assignment.dto.AccountsDTO;
import com.ngoclbph26472.assignment.entity.Accounts;
import jakarta.mail.MessagingException;
//import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountsDTO> getAll();

    AccountsDTO create(AccountsDTO accountsDTO);

    Optional<Accounts> findByUserName(String name);

    boolean checkExistEmail(String email) throws MessagingException;

    boolean updatePass(String pass);
}
