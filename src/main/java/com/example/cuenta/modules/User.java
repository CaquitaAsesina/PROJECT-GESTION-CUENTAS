package com.example.cuenta.modules;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "administrador")
    private String adminstrador;

    @Column(name = "cuentas")
    private Integer cuentas;

    private LocalDateTime fecha_registro;

}
