package com.example.cuenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cuenta.dto.AccountDto;
import com.example.cuenta.modules.Account;
import com.example.cuenta.modules.Gmail;
import com.example.cuenta.modules.User;
import com.example.cuenta.repository.AccountRepository;
import com.example.cuenta.repository.GmailRepository;
import com.example.cuenta.repository.UserRepository;

@Service("AccountService")
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GmailRepository gmailRepository;

    @Autowired
    private AccountRepository accountRepository;

    public AccountDto createAccount(AccountDto cuenta) {
        User user = userRepository.findById(cuenta.getUser_id()).orElseThrow();
        Gmail gmail = gmailRepository.findById(cuenta.getGmail_id()).orElseThrow();
        if (!gmail.getUser().getId().equals(user.getId())) {
            return null;
        }
        Account account = new Account();
        account.setUser(user);
        account.setGmail(gmail);
        account.setTipo(cuenta.getTipo());
        account.setUsuario(cuenta.getUsuario());
        account.setContraseña(cuenta.getContraseña());
        if (cuenta.getActivo() == null) {
            account.setActivo(false);
        } else {
            account.setActivo(cuenta.getActivo());
        }
        Account accountSave = accountRepository.save(account);
        return convertToDTO(accountSave);
    }

    private AccountDto convertToDTO(Account cuenta) {
        AccountDto dto = new AccountDto();
        dto.setId(cuenta.getId());
        dto.setUser_id(cuenta.getUser().getId());
        dto.setGmail_id(cuenta.getGmail().getId());
        dto.setTipo(cuenta.getTipo());
        dto.setUsuario(cuenta.getUsuario());
        dto.setContraseña(cuenta.getContraseña());
        dto.setActivo(cuenta.getActivo());
        dto.setFechaRegistro(cuenta.getFechaRegistro());
        dto.setFechaActualizacion(cuenta.getFechaActualizacion());
        return dto;
    }

}
