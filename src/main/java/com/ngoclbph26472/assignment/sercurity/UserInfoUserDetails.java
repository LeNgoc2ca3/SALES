package com.ngoclbph26472.assignment.sercurity;

import com.ngoclbph26472.assignment.entity.Accounts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoUserDetails implements UserDetails {//cung cấp các phương thức để truy cập các thông tin cơ bản của người dùng

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(Accounts accounts) {
        username = accounts.getUsername();
        password = accounts.getPassword();
//        authorities = Arrays.stream(accounts.getRole().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        // Tạo danh sách các quyền từ chuỗi quyền lấy từ tài khoản
        String[] roles = accounts.getRole().split(",");
        authorities = new ArrayList<>();
        for (String role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
//            System.out.println(authorities);
//            tạo danh sách các quyền từ chuỗi quyền của tài khoản, bằng cách sử dụng phương thức split()
//            để tách chuỗi thành các phần tử, sau đó tạo đối tượng SimpleGrantedAuthority cho mỗi phần tử và
//            thêm vào danh sách authorities
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {//trả về true nếu tài khoản của người dùng chưa hết hạn
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//trả về true nếu người dùng chưa bị khóa
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {// trả về true nếu chứng thực (mật khẩu) của người dùng chưa hết hạn
        return true;
    }

    @Override
    public boolean isEnabled() {//trả về true nếu người dùng đã được kích hoạt
        return true;
    }
}
