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

import jakarta.transaction.Transactional;

@Service("GmailService")
public class GmailService {

    @Autowired
    private GmailRepository gmailRepository;

    @Autowired
    private UserRepository userRepository;

    // GET
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

    // POST
    public GmailDto createGmail(GmailDto cuenta) {
        if (gmailRepository.existsByCorreo(cuenta.getCorreo())) {
            return null;
        }
        User user = userRepository.findById(cuenta.getUser_id()).orElseThrow();

        Gmail gmail = new Gmail();
        gmail.setUser(user);
        user.setCuentas(user.getCuentas() + 1);
        gmail.setCorreo(cuenta.getCorreo());
        gmail.setContraseña(cuenta.getContraseña());
        if (cuenta.getEstado() == null) {
            cuenta.setEstado("DESCARTABLE");
        } else {
            gmail.setEstado(cuenta.getEstado());
        }
        Gmail gmailSave = gmailRepository.save(gmail);
        userRepository.save(user);
        return convertToDTO(gmailSave);
    }

    // PUT
    public GmailDto updateGmail(Long id, GmailDto cuenta) {
        Gmail gmail = gmailRepository.findById(id).orElseThrow();
        gmail.setCorreo(cuenta.getCorreo());
        gmail.setContraseña(cuenta.getContraseña());
        gmail.setEstado(cuenta.getEstado());
        Gmail updateGmail = gmailRepository.save(gmail);
        return convertToDTO(updateGmail);
    }

    // PATCH
    public GmailDto updateCorreo(Long id, String correo) {
        Gmail gmail = gmailRepository.findById(id).orElseThrow();
        if (!gmail.getCorreo().equalsIgnoreCase(correo)) {
            if (gmailRepository.existsByCorreo(correo)) {
                return null;
            }
        } else {
            return null;
        }
        gmail.setCorreo(correo);
        Gmail updateGmail = gmailRepository.save(gmail);
        return convertToDTO(updateGmail);
    }

    public GmailDto updateContraseña(Long id, String contraseña) {
        Gmail gmail = gmailRepository.findById(id).orElseThrow();
        if (gmail.getContraseña().equalsIgnoreCase(contraseña)) {
            return null;
        }
        gmail.setContraseña(contraseña);
        Gmail updateContraseña = gmailRepository.save(gmail);
        return convertToDTO(updateContraseña);
    }

    public GmailDto updateEstado(Long id, String estado) {
        Gmail gmail = gmailRepository.findById(id).orElseThrow();
        if (gmail.getEstado().equalsIgnoreCase(estado)) {
            return null;
        }
        gmail.setEstado(estado);
        Gmail updateEstado = gmailRepository.save(gmail);
        return convertToDTO(updateEstado);
    }

    // DELETE
    @Transactional
    public GmailDto deleteGmail(Long id) {
        Gmail gmail = gmailRepository.findById(id).orElseThrow();
        gmailRepository.delete(gmail);
        return convertToDTO(gmail);
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
