package com.example.cuenta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cuenta.dto.UserDto;
import com.example.cuenta.modules.User;
import com.example.cuenta.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service("UserService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto usuario) {
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

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO).orElseThrow();
    }

    public UserDto getUserByAdministrador(String administrador) {
        return userRepository.findByAdministrador(administrador).map(this::convertToDTO).orElseThrow();
    }

    public UserDto updateUser(Long id, UserDto usuario) {
        User user = userRepository.findById(id).orElseThrow();
        if (!user.getAdministrador().equalsIgnoreCase(usuario.getAdministrador())) {
            if (userRepository.existsByAdministrador(usuario.getAdministrador())) {
                return null;
            }
        } else {
            return null;
        }
        user.setAdministrador(usuario.getAdministrador());
        user.setCuentas(usuario.getCuentas());
        User upadeUser = userRepository.save(user);
        return convertToDTO(upadeUser);
    }

    @Transactional
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user.getCuentas() != null && user.getCuentas() > 0) {
            return null;
        }
        userRepository.deleteById(id);
        return convertToDTO(user);
    }

    public UserDto convertToDTO(User usuario) {
        UserDto dto = new UserDto();
        dto.setId(usuario.getId());
        dto.setAdministrador(usuario.getAdministrador());
        dto.setCuentas(usuario.getCuentas());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        dto.setFechaActualizacion(usuario.getFechaActualizacion());
        return dto;
    }
}
