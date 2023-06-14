package com.ngoclbph26472.assignment.controller;

import com.ngoclbph26472.assignment.dto.AccountsDTO;
import com.ngoclbph26472.assignment.service.AccountService;
//import com.ngoclbph26472.assignment.service.MailService;
//import jakarta.mail.MessagingException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/assignment/account/")
public class AccountController {

    private static final String ESTRING = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

//    @Autowired
//    MailService mailService;

    @Autowired
    AccountService service;

    @PostMapping("")
    public String create(@Valid @ModelAttribute("acc") AccountsDTO dto, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("acc", dto);
            return "security/register";
        }
        service.create(dto);
        return "redirect:/security/login/form";

    }

    @GetMapping("reset")
    public String reset(@RequestParam(value = "mail") String email, Model model) throws MessagingException {
        if (service.checkExistEmail(email) == false) {
            model.addAttribute("email", "Email không tồn tại hoặc không đúng định dạng");
            return "security/email";
        } else
            model.addAttribute("message", "Vui lòng lấy pass ở mail");
            return "security/reset";
    }

    @PostMapping("reset")
    public String resetPass(Model model, @RequestParam(value = "password") String password,
                            @RequestParam(value = "passwordConfirm") String passwordConfirm,RedirectAttributes redirectAttributes) {
        if (service.updatePass(password) == false) {
            model.addAttribute("message", "Vui lòng lấy pass ở mail");
            return "security/reset";
        }
        if (passwordConfirm.equals(password)) {
            service.updatePass(password);
            redirectAttributes.addAttribute("success", "True");
            return "redirect:/security/login/form";

        }
        model.addAttribute("message", "PasswordConfirm không chính xác");
        return "security/reset";
    }
}
