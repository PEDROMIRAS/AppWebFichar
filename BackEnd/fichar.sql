-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS fichador_app CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE fichador_app;

-- Crear tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

-- Crear tabla fichajes
CREATE TABLE IF NOT EXISTS fichajes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo ENUM('entrada', 'salida') NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Insertar usuario de prueba
INSERT INTO usuarios (nombre, email, password_hash) VALUES
('Juan Pérez', 'juan@example.com', 'hash_de_ejemplo'),
('Ana Gómez', 'ana@example.com', 'hash_de_ejemplo');

-- Insertar fichaje de prueba
INSERT INTO fichajes (usuario_id, tipo) VALUES
(1, 'entrada'),
(2, 'salida');