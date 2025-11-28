CREATE DATABASE IF NOT EXISTS inventory_account;

USE inventory_account;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    administrador VARCHAR(20) UNIQUE NOT NULL,
    cuentas INT DEFAULT 0,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_user_admin_cuentas (administrador, cuentas),
    INDEX idx_user_fecha_cuentas (fecha_registro, cuentas),
    INDEX idx_user_cuentas_fecha (
        cuentas DESC,
        fecha_registro DESC
    )
);

CREATE TABLE gmail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(100) DEFAULT NULL,
    estado VARCHAR(30) DEFAULT 'DESCARTABLE',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id),
    INDEX idx_gmail_user_estado (user_id, estado),
    INDEX idx_gmail_estado_fecha (estado, fecha_registro),
    INDEX idx_gmail_correo_estado (correo, estado),
    INDEX idx_gmail_fecha_estado_user (
        fecha_registro,
        estado,
        user_id
    )
);