package com.ngoclbph26472.assignment.restcontroller;

import com.ngoclbph26472.assignment.dto.AccountsDTO;
import com.ngoclbph26472.assignment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment/account")
public class AccountRestController {

    @Autowired
    private AccountService service;

    @GetMapping("")
    public ResponseEntity<List<AccountsDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("")
    public ResponseEntity<AccountsDTO> create(@RequestBody AccountsDTO accountsDTO){
        service.create(accountsDTO);
        return ResponseEntity.ok().build();
    }
}
