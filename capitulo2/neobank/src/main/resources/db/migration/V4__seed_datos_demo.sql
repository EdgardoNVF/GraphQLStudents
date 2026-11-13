INSERT INTO clientes (id, nombre) VALUES
    ('100', 'Juan Pérez');

INSERT INTO cuentas (numero, cliente_id, saldo, moneda) VALUES
    ('5001', '100', 8000.00, 'MXN'),
    ('5002', '100', 3500.00, 'MXN'),
    ('5003', '100', 2500.00, 'MXN'),
    ('5004', '100', 6000.00, 'MXN'),
    ('5005', '100', 9500.00, 'MXN');

INSERT INTO transacciones (id, cuenta_numero, amount, status) VALUES
    ('T01', '5001', 500.00, 'COMPLETADA'),
    ('T02', '5001', 200.00, 'PENDIENTE'),
    ('T03', '5002', 1500.00, 'COMPLETADA');
