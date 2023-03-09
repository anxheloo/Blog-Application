package com.blogExample.springbootblogapplication.Controllers;

import com.blogExample.springbootblogapplication.Services.AccountService;
import com.blogExample.springbootblogapplication.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    AccountService accountService;

    @GetMapping("/register")
    public String getRegistrationPage(Model model)
    {
        Account account = new Account();
        model.addAttribute("account",account);
        return "registration";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account)
    {
        accountService.save(account);
        return "redirect:/";
    }

}
