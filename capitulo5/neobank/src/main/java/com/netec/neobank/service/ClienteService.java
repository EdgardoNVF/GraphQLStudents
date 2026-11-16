package com.netec.neobank.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netec.neobank.entity.ClienteEntity;
import com.netec.neobank.entity.CuentaEntity;
import com.netec.neobank.repository.ClienteJpaRepository;
import com.netec.neobank.repository.CuentaJpaRepository;


@Service 
public class ClienteService {
    private final ClienteJpaRepository clientes;
    private final CuentaJpaRepository cuentas;

    public ClienteService(ClienteJpaRepository clientes, CuentaJpaRepository cuentas) {
        this.clientes = clientes;
        this.cuentas = cuentas;
    }

    @Transactional (readOnly=true)
    public Map<String, Object> buscarPorId(String id){
        return clientes.findById(id)
                       .map(this::clienteAGraphQL)
                       .orElse(null);
    }
    
    @Transactional (readOnly=true)
    public List<Map<String, Object>> buscarCuentas(String clientId){
        return cuentas.findByCliente_Id(clientId)
                      .stream()
                      .map(this::cuentaAGraphQL)
                      .toList();
        
    }


    private Map<String, Object> clienteAGraphQL(ClienteEntity c){
        return Map.of(
            "id", c.getId(),
            "nombre", c.getNombre());
    }

    private Map<String, Object> cuentaAGraphQL(CuentaEntity c){
          return Map.of(
            "numero", c.getNumero(),
            "saldo", c.getSaldo(),
            "moneda",c.getMoneda()
          );
    }




}
