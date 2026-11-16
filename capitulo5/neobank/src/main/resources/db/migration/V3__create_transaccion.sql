CREATE TABLE transacciones (
    id VARCHAR(36) PRIMARY KEY,
    cuenta_numero VARCHAR(36) NOT NULL,
    amount NUMERIC(15,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_transacciones_cuenta
        FOREIGN KEY (cuenta_numero) REFERENCES cuentas(numero),
    CONSTRAINT chk_transacciones_amount CHECK (amount > 0)
);

CREATE INDEX idx_transacciones_cuenta_numero
    ON transacciones(cuenta_numero);
