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

import com.example.cuenta.dto.GmailDto;
import com.example.cuenta.dto.UserDto;
import com.example.cuenta.service.GmailService;
import com.example.cuenta.service.UserService;

@Controller
@RequestMapping("/gmails")
public class WebGmail {
    @Autowired
    @Qualifier("GmailService")
    private GmailService gmailService;

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @GetMapping
    public String listGmails(Model model) {
        List<GmailDto> gmails = gmailService.getAllGmails();
        model.addAttribute("gmails", gmails);
        return "gmails/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        GmailDto gmailDto = new GmailDto();
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("gmail", gmailDto);
        model.addAttribute("users", users);
        return "gmails/form";
    }

    @PostMapping
    public String createGmail(@ModelAttribute("gmail") GmailDto gmailDto, RedirectAttributes redirectAttributes) {
        gmailService.createGmail(gmailDto);
        redirectAttributes.addFlashAttribute("success", "Correo Gmail creado exitosamente");
        return "redirect:/gmails";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        GmailDto gmailDto = gmailService.getGmailById(id);
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("gmail", gmailDto);
        model.addAttribute("users", users);
        return "gmails/form";
    }

    @PostMapping("/update/{id}")
    public String updateGmail(@PathVariable("id") Long id, @ModelAttribute("gmail") GmailDto gmailDto,
            RedirectAttributes redirectAttributes) {
        gmailService.updateGmail(id, gmailDto);
        redirectAttributes.addFlashAttribute("success", "Correo Gmail actualizado exitosamente");
        return "redirect:/gmails";
    }

    @GetMapping("/delete/{id}")
    public String deleteGmail(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        gmailService.deleteGmail(id);
        redirectAttributes.addFlashAttribute("success", "Correo Gmail eliminado exitosamente");
        return "redirect:/gmails";
    }

    @GetMapping("/{id}")
    public String viewGmail(@PathVariable("id") Long id, Model model) {
        GmailDto gmailDto = gmailService.getGmailById(id);
        UserDto userDto = userService.getUserById(gmailDto.getUser_id());
        model.addAttribute("gmail", gmailDto);
        model.addAttribute("user", userDto);
        return "gmails/view";
    }

    @GetMapping("/user_id/{user_id}")
    public String listGmailsByUser(@PathVariable("user_id") Long user_id, Model model) {
        UserDto userDto = userService.getUserById(user_id);
        List<GmailDto> gmails = gmailService.getGmailsByUserId(user_id);
        model.addAttribute("gmails", gmails);
        model.addAttribute("user", userDto);
        return "gmails/list";
    }

    @GetMapping("/estado/{estado}")
    public String listGmailsByEstado(@PathVariable("estado") String estado, Model model) {
        List<GmailDto> gmails = gmailService.getGmailsByEstado(estado);
        model.addAttribute("gmails", gmails);
        model.addAttribute("filtroEstado", estado);
        return "gmails/list";
    }
}
