package com.example.cuenta.modules;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gmail", indexes = {
        @Index(name = "idx_gmail_user_estado", columnList = "user_id, estado"),
        @Index(name = "idx_gmail_estado_fecha", columnList = "estado, fecha_registro"),
        @Index(name = "idx_gmail_correo_estado", columnList = "correo, estado"),
        @Index(name = "idx_gmail_fecha_estado_user", columnList = "fecha_registro, estado, user_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Gmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_gmail_user"))
    private User user;

    @Column(name = "correo", unique = true, nullable = false, length = 100)
    private String correo;

    @Column(name = "contraseña", length = 100)
    private String contraseña;

    @Column(name = "estado", length = 30, columnDefinition = "VARCHAR(30) DEFAULT 'DESCARTABLE'")
    private String estado = "DESCARTABLE";

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCrate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (estado == null) {
            estado = "DESCARTABLE";
        }
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "gmail")
    private List<Account> accounts;
}
