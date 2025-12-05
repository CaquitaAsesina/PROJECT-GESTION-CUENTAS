package com.example.cuenta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cuenta.dto.AccountDto;
import com.example.cuenta.modules.Account;
import com.example.cuenta.modules.Gmail;
import com.example.cuenta.modules.User;
import com.example.cuenta.repository.AccountRepository;
import com.example.cuenta.repository.GmailRepository;
import com.example.cuenta.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service("AccountService")
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GmailRepository gmailRepository;

    @Autowired
    private AccountRepository accountRepository;

    // GET
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id) {
        return accountRepository.findById(id).map(this::convertToDTO).orElseThrow();
    }

    public List<AccountDto> getAccountsByUserId(Long user_id) {
        return accountRepository.findByUserId(user_id).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByGmailId(Long gmail_id) {
        return accountRepository.findByGmailId(gmail_id).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByTipo(String tipo) {
        return accountRepository.findByTipo(tipo).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByActivo(Boolean activo) {
        return accountRepository.findByActivo(activo).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByUserIdAndActivo(Long user_id, Boolean activo) {
        return accountRepository.findByUserIdAndActivo(user_id, activo).stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByUserIdAndTipo(Long user_id, String tipo) {
        return accountRepository.findByUserIdAndTipo(user_id, tipo).stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccountDto> getAccountsByTipoAndActivo(String tipo, Boolean activo) {
        return accountRepository.findByTipoAndActivo(tipo, activo).stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // POST
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

    // PUT
    public AccountDto updateAccount(Long id, AccountDto cuenta) {
        Account account = accountRepository.findById(id).orElseThrow();
        if (account == null) {
            return null;
        }
        account.setTipo(cuenta.getTipo());
        account.setUsuario(cuenta.getUsuario());
        account.setContraseña(cuenta.getContraseña());
        account.setActivo(cuenta.getActivo());
        Account updateAccount = accountRepository.save(account);
        return convertToDTO(updateAccount);
    }

    // PATCH

    // DELETE
    @Transactional
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        accountRepository.delete(account);
        return convertToDTO(account);
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
