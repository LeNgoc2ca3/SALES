package com.ngoclbph26472.assignment.config;


import com.ngoclbph26472.assignment.sercurity.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AuthConfig{

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
//        Mã hóa và giải mã mật khẩu
    }

    @Bean
    public UserDetailsService userDetailsService() {
//        Method userDetailsService() có tác dụng cung cấp thông tin user cho Spring Security
        return new UserInfoUserDetailsService();
    }


    //Phân quyền sử dụng và hình thức đăng nhập
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

//        Phân quyền
//        http.authorizeHttpRequests()
//                .requestMatchers("/assignment/cart","/security/**").permitAll()
////                .requestMatchers("/assignment/chart").hasRole("ADMIN")
//                .anyRequest().authenticated();

//        Xây dựng giao diện đăng nhập
        http.formLogin().loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/assignment/cart", false)
                .failureUrl("/security/login/form")
                .usernameParameter("username")
                .passwordParameter("password");
//        http.formLogin()
//                .loginPage("/security/login/form")//Địa chỉ dẫn tới form
//                .loginProcessingUrl("/security/login")//Địa chỉ xử lý thông tin đăng nhập
//                .defaultSuccessUrl("/assignment/category", false)//Địa chỉ khi đăng nhập vào thành công
//                .failureUrl("/security/login/form")//sai thông tin đưa về trang lỗi
//                .usernameParameter("username")
//                .passwordParameter("password");

//        Đăng xuất
        http.logout()
                .logoutUrl("/security/logoff")//địa chỉ khi đăng xuất
                .logoutSuccessUrl("/security/logoff/success");//Logoff thành công trả về trang login

//        Điều khiển lỗi truy cập không đúng vai trò
        http.exceptionHandling().accessDeniedPage("/security/unauthorized");
//        Oauth2 - Đăng nhập từ mạng xã hội
//        http.oauth2Login()
//                .loginPage("/auth/login/form")
//                .defaultSuccessUrl("/oauth2/login/success", true)
//                .failureUrl("/auth/login/error")
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorization");//Khai vào link đăng nhập
        return http.build();
    }

    //Quản lý dữ liệu người dùng
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }

//    Đoạn code trên định nghĩa một AuthenticationProvider bean trong ứng dụng. Nó trả về một đối tượng của lớp
//    DaoAuthenticationProvider, được cấu hình để sử dụng một UserDetailsService và một PasswordEncoder để xác thực
//    người dùng. Phương thức userDetailsService() và getPasswordEncoder() được gọi để cung cấp các dependency cho
//    DaoAuthenticationProvider. Nếu có lỗi xảy ra khi tạo bean, phương thức sẽ ném ra một Exception.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();//Cấu hình authen
    }

}
