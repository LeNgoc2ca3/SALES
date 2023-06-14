package com.ngoclbph26472.assignment.controller;

import com.ngoclbph26472.assignment.dto.AccountsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @GetMapping(value = "/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập");
        return "security/login";
    }

//    @GetMapping(value = "/login/success")
//    public String loginSuccess(Model model) {
//        model.addAttribute("message", "Đăng nhập thành công");
//        System.out.println("success");
//        return "security/login";
//    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Không đúng tài khoản hoặc mật khẩu");
        return "security/login";
    }

    @GetMapping(value = "/unauthorized")
    public String unauthorized(Model model) {
        model.addAttribute("message", "Không có quyền truy cập");
        return "security/login";
    }

    @GetMapping(value="/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất");
        return "security/login";
    }

    @GetMapping(value = "/resetpass")
    public String checkMail() {
        return "security/email";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("acc",new AccountsDTO());
        return "security/register";
    }

    @GetMapping(value = "/email")
    public String email() {
        return "security/email";
    }

}
