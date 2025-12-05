-- Active: 1764396596459@@127.0.0.1@3306@inventory_account

USE inventory_account;
DELIMITER $$

CREATE PROCEDURE vaciar_y_reiniciar_ids()
BEGIN
    -- Desactivar claves foráneas para permitir usar TRUNCATE
    SET FOREIGN_KEY_CHECKS = 0;

    TRUNCATE TABLE account;
    TRUNCATE TABLE gmail;
    TRUNCATE TABLE `user`;

    -- Reactivar claves foráneas
    SET FOREIGN_KEY_CHECKS = 1;
END$$

DELIMITER;

CALL vaciar_y_reiniciar_ids();
