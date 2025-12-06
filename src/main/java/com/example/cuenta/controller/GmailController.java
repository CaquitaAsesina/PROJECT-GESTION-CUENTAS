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

import com.example.cuenta.dto.GmailDto;
import com.example.cuenta.service.GmailService;

@RestController
@RequestMapping("/api/gmail")
public class GmailController {

    @Autowired
    @Qualifier("GmailService")
    private GmailService gmail;

    // GET
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

    @GetMapping("/search/all/stado/{estado}")
    public List<GmailDto> getGmailsByEstado(@PathVariable String estado) {
        return gmail.getGmailsByEstado(estado);
    }

    // POST
    @PostMapping("/save")
    public GmailDto createGmail(@RequestBody GmailDto cuenta) {
        return gmail.createGmail(cuenta);
    }

    // PUT
    @PutMapping("/update/id/{id}")
    public GmailDto updateGmail(@PathVariable Long id, @RequestBody GmailDto cuenta) {
        return gmail.updateGmail(id, cuenta);
    }

    // PATCH
    @PatchMapping("/modif/id/{id}/correo")
    public GmailDto updateCorreo(@PathVariable Long id, @RequestBody GmailDto cuenta) {
        return gmail.updateCorreo(id, cuenta.getCorreo());
    }

    @PatchMapping("/modif/id/{id}/contraseña")
    public GmailDto updateContraseña(@PathVariable Long id, @RequestBody GmailDto cuenta) {
        return gmail.updateContraseña(id, cuenta.getContraseña());
    }

    @PatchMapping("/modif/id/{id}/estado")
    public GmailDto updateEstado(@PathVariable Long id, @RequestBody GmailDto cuenta) {
        return gmail.updateEstado(id, cuenta.getEstado());
    }

    // DELETE
    @DeleteMapping("/delete/id/{id}")
    public GmailDto deleteGmail(@PathVariable Long id) {
        return gmail.deleteGmail(id);
    }

}
