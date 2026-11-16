package com.netec.neobank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="clientes")
public class ClienteEntity {

    @Id
    private String id;

    @Column(name="nombre", nullable = false, length = 150)
    private String nombre;
     
    protected ClienteEntity(){}

    public ClienteEntity(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


}
