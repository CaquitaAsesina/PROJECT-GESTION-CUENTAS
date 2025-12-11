package com.example.cuenta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cuenta.service.AccountService;
import com.example.cuenta.service.GmailService;
import com.example.cuenta.service.UserService;

@Controller
public class WebHome {
    @Autowired
    @Qualifier("UserService")
    private UserService userService;
    @Autowired

    @Qualifier("GmailService")
    private GmailService gmailService;

    @Autowired
    @Qualifier("AccountService")
    private AccountService accountService;

    @GetMapping("/")
    public String home(Model model) {
        long totalUsers = userService.getAllUsers().size();
        model.addAttribute("totalUsers", totalUsers);

        long totalGmails = gmailService.getAllGmails().size();
        model.addAttribute("totalGmails", totalGmails);

        long totalAccounts = accountService.getAllAccounts().size();
        model.addAttribute("totalAccounts", totalAccounts);

        long activeAccounts = accountService.getAccountsByActivo(true).size();
        model.addAttribute("activeAccounts", activeAccounts);

        long inactiveAccounts = accountService.getAccountsByActivo(false).size();
        model.addAttribute("inactiveAccounts", inactiveAccounts);
        return "home";
    }

}
