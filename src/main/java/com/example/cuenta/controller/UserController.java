package com.example.cuenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/save")
    public UserDto agregarUsuario(@RequestBody UserDto usuario) {
        return user.agregarUsuario(usuario);
    }
}
