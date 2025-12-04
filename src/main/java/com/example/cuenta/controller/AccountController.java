package com.example.cuenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

}
