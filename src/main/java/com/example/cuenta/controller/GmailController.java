package com.example.cuenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
}
