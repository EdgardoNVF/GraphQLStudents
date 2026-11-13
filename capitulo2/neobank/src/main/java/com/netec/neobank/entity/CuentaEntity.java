package com.netec.neobank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
public class CuentaEntity {

    @Id
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @Column(name = "saldo", nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo;

    @Column(name = "moneda", nullable = false, length = 3)
    private String moneda;

    protected CuentaEntity() { }

    public CuentaEntity(String numero, ClienteEntity cliente,
                       BigDecimal saldo, String moneda) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
        this.moneda = moneda;
    }

    public String getNumero() { return numero; }
    public ClienteEntity getCliente() { return cliente; }
    public BigDecimal getSaldo() { return saldo; }
    public String getMoneda() { return moneda; }
}
