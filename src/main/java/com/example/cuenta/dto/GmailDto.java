package com.example.cuenta.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GmailDto {

    private Long id;
    private Long user_id;
    private String correo;
    private String contraseña;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    public GmailDto(Long user_id, String correo, String contraseña, String estado) {
        this.user_id = user_id;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
    }

}
