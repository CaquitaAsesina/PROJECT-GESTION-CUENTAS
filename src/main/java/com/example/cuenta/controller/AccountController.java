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

    // GET
    @GetMapping("/all")
    public List<AccountDto> getAllAccounts() {
        return account.getAllAccounts();
    }

    @GetMapping("/search/id/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        return account.getAccountById(id);
    }

    @GetMapping("/search/all/user_id/{user_id}")
    public List<AccountDto> getAccountsByUserId(@PathVariable Long user_id) {
        return account.getAccountsByUserId(user_id);
    }

    @GetMapping("/search/all/gmail_id/{gmail_id}")
    public List<AccountDto> getAccountsByGmailId(@PathVariable Long gmail_id) {
        return account.getAccountsByGmailId(gmail_id);
    }

    @GetMapping("/search/all/tipo/{tipo}")
    public List<AccountDto> getAccountsByTipo(@PathVariable String tipo) {
        return account.getAccountsByTipo(tipo);
    }

    @GetMapping("/search/all/activo/{activo}")
    public List<AccountDto> getAccountsByActivo(@PathVariable Boolean activo) {
        return account.getAccountsByActivo(activo);
    }

    @GetMapping("/search/all/user_id/{user_id}/activo/{activo}")
    public List<AccountDto> getAccountsByUserIdAndActivo(@PathVariable Long user_id, @PathVariable Boolean activo) {
        return account.getAccountsByUserIdAndActivo(user_id, activo);
    }

    @GetMapping("/search/all/user_id/{user_id}/tipo/{tipo}")
    public List<AccountDto> getAccountsByUserIdAndTipo(@PathVariable Long user_id, @PathVariable String tipo) {
        return account.getAccountsByUserIdAndTipo(user_id, tipo);
    }

    @GetMapping("/search/tall/ipo/{tipo}/activo/{activo}")
    public List<AccountDto> getAccountsByTipoAndActivo(@PathVariable String tipo, @PathVariable Boolean activo) {
        return account.getAccountsByTipoAndActivo(tipo, activo);
    }

    // POST
    @PostMapping("/save")
    public AccountDto createAccount(@RequestBody AccountDto cuenta) {
        return account.createAccount(cuenta);
    }
    // PUT

    // PATCH

    // DELETE

}
