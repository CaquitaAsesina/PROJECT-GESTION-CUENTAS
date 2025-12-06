package com.example.cuenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cuenta.dto.UserDto;
import com.example.cuenta.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService user;

    // GET
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return user.getAllUsers();
    }

    @GetMapping("/search/id/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return user.getUserById(id);
    }

    @GetMapping("/search/administrador/{administrador}")
    public UserDto getUserByAdministrador(@PathVariable String administrador) {
        return user.getUserByAdministrador(administrador);
    }

    // POST
    @PostMapping("/save")
    public UserDto createUser(@RequestBody UserDto usuario) {
        return user.createUser(usuario);
    }

    // PUT
    @PutMapping("/update/id/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto usuario) {
        return user.updateUser(id, usuario);
    }

    // PATCH
    @PatchMapping("/modif/id/{id}/administrador")
    public UserDto updateAdministrador(@PathVariable Long id, @RequestBody UserDto usuario) {
        return user.updateAdministrador(id, usuario.getAdministrador());
    }

    @PatchMapping("/modif/id/{id}/cuentas")
    public UserDto updateCuentas(@PathVariable Long id, @RequestBody UserDto usuario) {
        return user.updateCuentas(id, usuario.getCuentas());
    }

    // DELETE
    @DeleteMapping("/delete/id/{id}")
    public UserDto deleteUser(@PathVariable Long id) {
        return user.deleteUser(id);
    }

}
