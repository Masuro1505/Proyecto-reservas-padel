-- Usuarios
INSERT INTO usuario (nombre, apellidos, telefono, email) VALUES
('Juan', 'Pérez', '600111222', 'juan.perez@email.com'),
('Laura', 'García', '600333444', 'laura.garcia@email.com'),
('Carlos', 'López', '600555666', 'carlos.lopez@email.com'),
('Ana', 'Martínez', '600777888', 'ana.martinez@email.com');

-- Pistas
INSERT INTO pista (nombre, tipo, estado) VALUES
('Pista 1', 'Cubierta', 'Disponible'),
('Pista 2', 'Exterior', 'Disponible'),
('Pista 3', 'Cubierta', 'Disponible'),
('Pista Central', 'Cubierta', 'Mantenimiento');

-- Tarifas
INSERT INTO tarifa (tipo_pista, tipo_dia, franja_horaria, precio) VALUES
('Cubierta','Laborable','Mañana',18),
('Cubierta','Laborable','Tarde',22),
('Cubierta','FinDeSemana','Mañana',25),
('Cubierta','FinDeSemana','Tarde',30),

('Exterior','Laborable','Mañana',15),
('Exterior','Laborable','Tarde',18),
('Exterior','FinDeSemana','Mañana',20),
('Exterior','FinDeSemana','Tarde',24);

-- Reservas
INSERT INTO reserva (id_usuario, id_pista, id_tarifa, fecha, hora_inicio, hora_fin) VALUES
(1, 1, 2, '2026-03-15', '18:00:00', '19:30:00'),
(2, 2, 6, '2026-03-16', '10:00:00', '11:30:00'),
(3, 1, 4, '2026-03-21', '19:00:00', '20:30:00');

-- Pagos
INSERT INTO pago (id_reserva, fecha_pago, metodo_pago, cantidad) VALUES
(1, '2026-03-10', 'Tarjeta', 22),
(2, '2026-03-11', 'Bizum', 15),
(3, '2026-03-18', 'Efectivo', 30);