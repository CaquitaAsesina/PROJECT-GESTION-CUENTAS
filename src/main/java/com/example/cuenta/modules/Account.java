package com.example.cuenta.modules;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account", indexes = {
        @Index(name = "idx_account_user_estado_fecha", columnList = "user_id, activo, fecha_registro DESC"),
        @Index(name = "idx_account_tipo_estado_fecha", columnList = "tipo, activo, fecha_registro DESC"),
        @Index(name = "idx_account_activo_user_tipo", columnList = "activo, user_id, tipo"),
        @Index(name = "idx_account_email_estado_fecha", columnList = "email_id, activo, fecha_registro"),
        @Index(name = "idx_account_usuario_tipo_activo", columnList = "usuario, tipo, activo"),
        @Index(name = "idx_account_fecha_estado_user", columnList = "fecha_registro, activo, user_id"),
        @Index(name = "idx_account_user_email_tipo", columnList = "user_id, email_id, tipo"),
        @Index(name = "idx_account_tipo_fecha_estado", columnList = "tipo, fecha_registro, activo")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_account_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id", nullable = false, foreignKey = @ForeignKey(name = "fk_account_gmail"))
    private Gmail gmail;

    @Column(name = "tipo", nullable = false, length = 150)
    private String tipo;

    @Column(name = "usuario", nullable = false, length = 150)
    private String usuario;

    @Column(name = "contraseña", nullable = false, length = 150)
    private String contraseña;

    @Column(name = "activo", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean activo = false;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (activo == null) {
            activo = false;
        }
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
