package com.netec.neobank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netec.neobank.entity.CuentaEntity;

public interface CuentaJpaRepository 
    extends JpaRepository<CuentaEntity, String>{
    
        List<CuentaEntity> findByCliente_Id(String clienteId);

}
