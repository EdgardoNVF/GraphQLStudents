package com.netec.neobank.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.netec.neobank.entity.TransaccionEntity;

public interface TransaccionJpaRepository 
    extends JpaRepository<TransaccionEntity, String>{

        @EntityGraph(attributePaths = "cuenta")
        List<TransaccionEntity> findByCuenta_NumeroIn(
            Collection<String> numeros
        );

}
