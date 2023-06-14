package com.ngoclbph26472.assignment.service.ipml;

import com.ngoclbph26472.assignment.dto.AccountsDTO;
import com.ngoclbph26472.assignment.entity.Accounts;
import com.ngoclbph26472.assignment.repositories.AccountRepository;
import com.ngoclbph26472.assignment.service.AccountService;
import com.ngoclbph26472.assignment.service.MailService;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountIpml implements AccountService {

    private static final int RANDOM_NUM = (int) Math.floor(Math.random() * (9999 - 1000 + 1) + 1000);

    private static String emailReset = null;

    @Autowired
    MailService mailService;

    @Autowired
    AccountRepository repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AccountsDTO> getAll() {
        List<Accounts> listUserInfo = repo.findAll();
        TypeToken<List<AccountsDTO>> typeToken = new TypeToken<>() {
        };
        List<AccountsDTO> listAccountsDTO = mapper.map(listUserInfo, typeToken.getType());
        return listAccountsDTO;
    }

    @Override
    public AccountsDTO create(AccountsDTO accountsDTO) {
        Accounts accounts = mapper.map(accountsDTO, Accounts.class);
        accounts.setId(0l);
        accounts.setActived((byte) 1);
        accounts.setPhoto(" ");
        accounts.setRole("USER");
        accounts.setPassword(passwordEncoder.encode(accounts.getPassword()));
        repo.save(accounts);
        return accountsDTO;
    }

    @Override
    public Optional<Accounts> findByUserName(String name) {
        return repo.findByUsername(name);
    }

    @Override
    public boolean checkExistEmail(String email) throws MessagingException {
        if(repo.existsAccountsByEmail(email)){
            System.out.println(repo.existsAccountsByEmail(email));
            emailReset = email;
            mailService.pushEmail(email, "PASS TO RESET",  "Password:" + RANDOM_NUM );
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePass(String pass) {
        if(pass.equals(String.valueOf(RANDOM_NUM))){
            repo.updatePassword(emailReset, passwordEncoder.encode(pass));
            return true;
        }
        return false;
    }
}
