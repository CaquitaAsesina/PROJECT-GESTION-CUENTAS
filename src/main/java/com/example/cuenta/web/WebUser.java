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

import com.example.cuenta.dto.UserDto;
import com.example.cuenta.service.UserService;

@Controller
@RequestMapping("/users")
public class WebUser {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "users/form";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes) {
        userService.createUser(userDto);
        redirectAttributes.addFlashAttribute("success", "Usuario creado exitosamente.");
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        return "users/form";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") UserDto userDto,
            RedirectAttributes redirectAttributes) {
        userService.updateUser(id, userDto);
        redirectAttributes.addFlashAttribute("success", "Usuario actualizado exitosamente.");
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("success", "Usuario eliminado exitosamente");
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") Long id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        return "users/view";
    }
}
