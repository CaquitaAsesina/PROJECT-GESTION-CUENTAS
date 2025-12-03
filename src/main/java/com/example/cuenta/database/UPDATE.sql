-- Active: 1764396596459@@127.0.0.1@3306@inventory_account
-- ===== SCRIPT DE MIGRACIÓN =====
-- Agrega la columna fecha_actualizacion a las 3 tablas
-- Ejecuta este script en tu base de datos MySQL

USE inventory_account;

-- ===== TABLA USER =====
-- Agrega la columna fecha_actualizacion a la tabla user
ALTER TABLE user
ADD COLUMN fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER fecha_registro;

-- Inicializa fecha_actualizacion con el valor de fecha_registro para registros existentes
UPDATE user
SET
    fecha_actualizacion = fecha_registro
WHERE
    fecha_actualizacion IS NULL;

-- ===== TABLA GMAIL =====
-- Agrega la columna fecha_actualizacion a la tabla gmail
ALTER TABLE gmail
ADD COLUMN fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER fecha_registro;

-- Inicializa fecha_actualizacion con el valor de fecha_registro para registros existentes
UPDATE gmail
SET
    fecha_actualizacion = fecha_registro
WHERE
    fecha_actualizacion IS NULL;

-- ===== TABLA ACCOUNT =====
-- Agrega la columna fecha_actualizacion a la tabla account
ALTER TABLE account
ADD COLUMN fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER fecha_registro;

-- Inicializa fecha_actualizacion con el valor de fecha_registro para registros existentes
UPDATE account
SET
    fecha_actualizacion = fecha_registro
WHERE
    fecha_actualizacion IS NULL;

-- ===== VERIFICACIÓN =====
-- Verifica que las columnas se hayan agregado correctamente
DESCRIBE user;

DESCRIBE gmail;

DESCRIBE account;

-- ===== PRUEBA DE ACTUALIZACIÓN =====
-- Prueba que la fecha_actualizacion se actualice automáticamente
-- Ejemplo: Actualiza un usuario y verifica que fecha_actualizacion cambie
-- SELECT id, administrador, fecha_registro, fecha_actualizacion FROM user WHERE id = 1;
-- UPDATE user SET cuentas = cuentas + 1 WHERE id = 1;
-- SELECT id, administrador, fecha_registro, fecha_actualizacion FROM user WHERE id = 1;

/* ===== EXPLICACIÓN =====

ON UPDATE CURRENT_TIMESTAMP:
- MySQL actualiza automáticamente este campo cada vez que se modifica el registro
- No necesitas hacer nada en el código, MySQL lo hace automáticamente
- Esto complementa el @PreUpdate de JPA

DIFERENCIA ENTRE LAS DOS FECHAS:
- fecha_registro: Se establece UNA VEZ al crear el registro (NUNCA cambia)
- fecha_actualizacion: Se actualiza CADA VEZ que se modifica el registro

EJEMPLO DE COMPORTAMIENTO:
1. Creas un usuario:
- fecha_registro = 2025-01-15 10:00:00
- fecha_actualizacion = 2025-01-15 10:00:00

2. Actualizas el usuario (cambias nombre):
- fecha_registro = 2025-01-15 10:00:00 (NO cambia)
- fecha_actualizacion = 2025-01-15 15:30:00 (SÍ cambia)

3. Vuelves a actualizar:
- fecha_registro = 2025-01-15 10:00:00 (NO cambia)
- fecha_actualizacion = 2025-01-15 18:45:00 (SÍ cambia)

*/