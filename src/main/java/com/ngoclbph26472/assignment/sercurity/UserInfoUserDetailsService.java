package com.ngoclbph26472.assignment.sercurity;

import com.ngoclbph26472.assignment.entity.Accounts;
import com.ngoclbph26472.assignment.repositories.AccountRepository;
import com.ngoclbph26472.assignment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Ta sẽ tìm kiếm trong CSDL, record thỏa mãn username
        Optional<Accounts> accounts = repository.findByUsername(username);
        System.out.println(accounts.toString());
//        UserInfoUserDetailsService userInfoUserDetails = null;
//        UserInfoUserDetails userInfoUserDetails = null;
//        if(accounts == null){
//            throw new UsernameNotFoundException("Not found");
//        } else {
//            userInfoUserDetails = new UserInfoUserDetails();
//            userInfoUserDetails.setAccounts(accounts);
//
//        }
//        return userInfoUserDetails;
        return accounts.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
        //sẽ trả về một implementation của UserDetails
    }
}
