package com.example.cuenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cuenta.dto.GmailDto;
import com.example.cuenta.service.GmailService;

@RestController
@RequestMapping("/api/gmail")
public class GmailController {

    @Autowired
    @Qualifier("GmailService")
    private GmailService gmail;

    @PostMapping("/save")
    public GmailDto createGmail(@RequestBody GmailDto cuenta) {
        return gmail.createGmail(cuenta);
    }

    @GetMapping("/all")
    public List<GmailDto> getAllGmails() {
        return gmail.getAllGmails();
    }

    @GetMapping("/search/id/{id}")
    public GmailDto getGmailById(@PathVariable Long id) {
        return gmail.getGmailById(id);
    }

    @GetMapping("/search/correo/{correo}")
    public GmailDto getGmailByCorreo(@PathVariable String correo) {
        return gmail.getGmailByCorreo(correo);
    }

    @GetMapping("/search/all/id/{id}")
    public List<GmailDto> getGmailsByUserId(@PathVariable Long id) {
        return gmail.getGmailsByUserId(id);
    }

    @GetMapping("/search/all/estado/{estado}")
    public List<GmailDto> getGmailsByEstado(@PathVariable String estado) {
        return gmail.getGmailsByEstado(estado);
    }

}
