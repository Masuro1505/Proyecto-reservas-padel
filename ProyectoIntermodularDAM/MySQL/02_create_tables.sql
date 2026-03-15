-- Tabla Usuario
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Pista
CREATE TABLE pista (
    id_pista INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    tipo ENUM('Cubierta', 'Exterior') NOT NULL,
    estado ENUM('Disponible', 'Mantenimiento') DEFAULT 'Disponible'
);

-- Tabla Tarifa
CREATE TABLE tarifa (
    id_tarifa INT AUTO_INCREMENT PRIMARY KEY,
    tipo_pista ENUM('Cubierta', 'Exterior') NOT NULL,
    tipo_dia ENUM('Laborable', 'FinDeSemana') NOT NULL,
    franja_horaria ENUM('Mañana', 'Tarde') NOT NULL,
    precio DECIMAL(6,2) NOT NULL
);

-- Tabla Reserva
CREATE TABLE reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_pista INT NOT NULL,
    id_tarifa INT,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,

    CONSTRAINT fk_reserva_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON DELETE CASCADE,

    CONSTRAINT fk_reserva_pista
        FOREIGN KEY(id_pista) REFERENCES pista(id_pista)
        ON DELETE CASCADE,

    CONSTRAINT fk_reserva_tarifa
        FOREIGN KEY(id_tarifa) REFERENCES tarifa(id_tarifa)
        ON DELETE CASCADE
);

-- Tabla Pago
CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    fecha_pago DATE,
    metodo_pago ENUM('Tarjeta', 'Efectivo', 'Bizum'), 
    cantidad DECIMAL(6,2),

    CONSTRAINT fk_pago_reserva
        FOREIGN KEY(id_reserva) REFERENCES reserva(id_reserva)
        ON DELETE CASCADE
);