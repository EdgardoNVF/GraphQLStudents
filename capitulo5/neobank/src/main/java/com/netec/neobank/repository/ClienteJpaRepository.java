package com.netec.neobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netec.neobank.entity.ClienteEntity;

public interface ClienteJpaRepository 
    extends JpaRepository<ClienteEntity,String>{
   
}
