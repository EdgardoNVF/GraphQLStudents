CREATE TABLE cuentas (
    numero VARCHAR(36) PRIMARY KEY,
    cliente_id VARCHAR(36) NOT NULL,
    saldo NUMERIC(15,2) NOT NULL,
    moneda VARCHAR(3) NOT NULL,
    CONSTRAINT fk_cuentas_cliente
        FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE INDEX idx_cuentas_cliente_id ON cuentas(cliente_id);
