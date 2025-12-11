package com.example.cuenta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.cuenta.dto.AccountDto;
import com.example.cuenta.dto.GmailDto;
import com.example.cuenta.dto.UserDto;
import com.example.cuenta.service.AccountService;
import com.example.cuenta.service.GmailService;
import com.example.cuenta.service.UserService;

@Controller
@RequestMapping("/accounts")
public class WebAccount {

    @Autowired
    @Qualifier("AccountService")
    private AccountService accountService;

    @Autowired
    @Qualifier("GmailService")
    private GmailService gmailService;

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @GetMapping
    public String listAccounts(Model model) {
        List<AccountDto> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "accounts/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        AccountDto accountDto = new AccountDto();

        List<UserDto> users = userService.getAllUsers();
        List<GmailDto> gmails = gmailService.getAllGmails();

        model.addAttribute("account", accountDto);
        model.addAttribute("users", users);
        model.addAttribute("gmails", gmails);
        return "accounts/form";
    }

    @PostMapping
    public String createAccount(@ModelAttribute("account") AccountDto accountDto,
            RedirectAttributes redirectAttributes) {
        accountService.createAccount(accountDto);
        redirectAttributes.addFlashAttribute("success", "Cuenta creada correctamente");
        return "redirect:/accounts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        AccountDto accountDto = accountService.getAccountById(id);
        List<UserDto> users = userService.getAllUsers();
        List<GmailDto> gmails = gmailService.getAllGmails();
        model.addAttribute("account", accountDto);
        model.addAttribute("users", users);
        model.addAttribute("gmails", gmails);
        return "accounts/form";
    }

    @PostMapping("update/{id}")
    public String updateAccount(@PathVariable("id") Long id, @ModelAttribute("account") AccountDto accountDto,
            RedirectAttributes redirectAttributes) {
        accountService.updateAccount(id, accountDto);
        redirectAttributes.addFlashAttribute("success", "Cuenta actualizada correctamente");
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        accountService.deleteAccount(id);
        redirectAttributes.addFlashAttribute("success", "Cuenta eliminada exitosamente");
        return "redirect:/accounts";
    }

    @GetMapping("/{id}")
    public String viewAccount(@PathVariable("id") Long id, Model model) {
        AccountDto accountDto = accountService.getAccountById(id);
        UserDto userDto = userService.getUserById(id);
        GmailDto gmailDto = gmailService.getGmailById(id);

        model.addAttribute("account", accountDto);
        model.addAttribute("user", userDto);
        model.addAttribute("gmail", gmailDto);
        return "accounts/view";
    }

    @GetMapping("/user/{user_id}")
    public String listAccountsByUser(@PathVariable("user_id") Long user_id, Model model) {
        UserDto userDto = userService.getUserById(user_id);
        List<AccountDto> accounts = accountService.getAccountsByUserId(user_id);
        model.addAttribute("accounts", accounts);
        model.addAttribute("user", userDto);
        return "accounts/list";
    }

    @GetMapping("/tipo/{tipo}")
    public String listAccountsByTipo(@PathVariable("tipo") String tipo, Model model) {
        List<AccountDto> accounts = accountService.getAccountsByTipo(tipo);
        model.addAttribute("accounts", accounts);
        model.addAttribute("filtroTipo", tipo);
        return "accounts/list";
    }

    @GetMapping("/activo/{activo}")
    public String listAccountsByActivo(@PathVariable("activo") Boolean activo, Model model) {
        List<AccountDto> accounts = accountService.getAccountsByActivo(activo);
        model.addAttribute("accounts", accounts);
        model.addAttribute("filtroActivo", activo);
        return "accounts/list";
    }

    @GetMapping("/toggle/{id}")
    public String toggleAccountStatus(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        AccountDto accountDto = accountService.getAccountById(id);
        accountDto.setActivo(!accountDto.getActivo());
        accountService.updateAccount(id, accountDto);
        String mensaje = accountDto.getActivo() ? "Cuenta activada" : "Cuenta desactivada";
        redirectAttributes.addFlashAttribute("success", mensaje);
        return "redirect:/accounts";

    }

}
