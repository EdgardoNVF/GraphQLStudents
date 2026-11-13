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
@Table(name = "transacciones")
public class TransaccionEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cuenta_numero", nullable = false)
    private CuentaEntity cuenta;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    protected TransaccionEntity() { }

    public TransaccionEntity(String id, CuentaEntity cuenta,
                            BigDecimal amount, String status) {
        this.id = id;
        this.cuenta = cuenta;
        this.amount = amount;
        this.status = status;
    }

    public String getId() { return id; }
    public CuentaEntity getCuenta() { return cuenta; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
}

