package com.example.cuenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cuenta.dto.UserDto;
import com.example.cuenta.modules.User;
import com.example.cuenta.repository.UserRepository;

@Service("UserService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto agregarUsuario(UserDto usuario) {
        Boolean results = userRepository.existsByAdministrador(usuario.getAdministrador());
        if (results == true) {
            return null;
        }
        User user = new User();
        user.setAdministrador(usuario.getAdministrador());
        if (usuario.getCuentas() == null) {
            user.setCuentas(0);
        } else {
            user.setCuentas(usuario.getCuentas());
        }
        User userSave = userRepository.save(user);
        return convertToDTO(userSave);
    }

    public UserDto convertToDTO(User usuario) {
        return new UserDto(usuario.getAdministrador(), usuario.getCuentas());
    }
}
