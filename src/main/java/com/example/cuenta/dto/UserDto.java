package com.example.cuenta.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String administrador;
    private Integer cuentas;
    private LocalDateTime fechaRegistro;

    public UserDto(String administrador, Integer cuentas) {
        this.administrador = administrador;
        this.cuentas = cuentas;
    }

}
