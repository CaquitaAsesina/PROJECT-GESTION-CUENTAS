package com.example.cuenta.modules;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_user_admin_cuentas", columnList = "administrador, cuentas"),
        @Index(name = "idx_user_fecha_cuentas", columnList = "fecha_registro, cuentas"),
        @Index(name = "idx_user_cuentas_fecha", columnList = "cuentas DESC, fecha_registro DESC")
})
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "administrador", unique = true, nullable = false, length = 20)
    private String adminstrador;

    @Column(name = "cuentas", columnDefinition = "INT DEFAULT 0")
    private Integer cuentas = 0;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
    }

    @OneToMany(mappedBy = "user")
    private List<Gmail> gmails;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

}
