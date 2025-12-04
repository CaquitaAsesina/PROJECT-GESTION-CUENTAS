package com.example.cuenta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cuenta.dto.GmailDto;
import com.example.cuenta.modules.Gmail;
import com.example.cuenta.modules.User;
import com.example.cuenta.repository.GmailRepository;
import com.example.cuenta.repository.UserRepository;

@Service("GmailService")
public class GmailService {

    @Autowired
    private GmailRepository gmailRepository;

    @Autowired
    private UserRepository userRepository;

    public GmailDto createGmail(GmailDto cuenta) {
        if (gmailRepository.existsByCorreo(cuenta.getCorreo())) {
            return null;
        }
        User user = userRepository.findById(cuenta.getUser_id()).orElseThrow();

        Gmail gmail = new Gmail();
        gmail.setUser(user);
        gmail.setCorreo(cuenta.getCorreo());
        gmail.setContraseña(cuenta.getContraseña());
        if (cuenta.getEstado() == null) {
            cuenta.setEstado("DESCARTABLE");
        } else {
            gmail.setEstado(cuenta.getEstado());
        }
        Gmail gmailSave = gmailRepository.save(gmail);
        return convertToDTO(gmailSave);
    }

    public GmailDto getGmailById(Long id) {
        return gmailRepository.findById(id).map(this::convertToDTO).orElseThrow();
    }

    public GmailDto getGmailByCorreo(String correo) {
        return gmailRepository.findByCorreo(correo).map(this::convertToDTO).orElseThrow();
    }

    public List<GmailDto> getAllGmails() {
        return gmailRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<GmailDto> getGmailsByUserId(Long user_id) {
        return gmailRepository.findByUserId(user_id).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<GmailDto> getGmailsByEstado(String estado) {
        return gmailRepository.findByEstado(estado).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private GmailDto convertToDTO(Gmail cuenta) {
        GmailDto dto = new GmailDto();
        dto.setId(cuenta.getId());
        dto.setUser_id(cuenta.getUser().getId());
        dto.setCorreo(cuenta.getCorreo());
        dto.setContraseña(cuenta.getContraseña());
        dto.setEstado(cuenta.getEstado());
        dto.setFechaRegistro(cuenta.getFechaRegistro());
        dto.setFechaActualizacion(cuenta.getFechaActualizacion());
        return dto;

    }
}
