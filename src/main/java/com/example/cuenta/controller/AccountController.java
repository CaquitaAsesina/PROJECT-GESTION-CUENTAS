package com.example.cuenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cuenta.dto.AccountDto;
import com.example.cuenta.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    @Qualifier("AccountService")
    private AccountService account;

    @PostMapping("/save")
    public AccountDto createAccount(@RequestBody AccountDto cuenta) {
        return account.createAccount(cuenta);
    }

    @GetMapping("/all")
    public List<AccountDto> getAllAccounts() {
        return account.getAllAccounts();
    }

    @GetMapping("/search/id/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        return account.getAccountById(id);
    }

    @GetMapping("/search/user_id/{user_id}")
    public List<AccountDto> getAccountsByUserId(Long user_id) {
        return account.getAccountsByUserId(user_id);
    }

}
