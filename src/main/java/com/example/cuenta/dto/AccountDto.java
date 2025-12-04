package com.example.cuenta.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private Long user_id;
    private Long gmail_id;
    private String tipo;
    private String usuario;
    private String contraseña;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    public AccountDto(Long user_id, Long gmail_id, String tipo, String usuario, String contraseña, Boolean activo) {
        this.user_id = user_id;
        this.gmail_id = gmail_id;
        this.tipo = tipo;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.activo = activo;
    }

}
