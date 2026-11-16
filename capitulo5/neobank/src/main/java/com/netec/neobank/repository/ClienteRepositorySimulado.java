package com.netec.neobank.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository 
public class ClienteRepositorySimulado {
    private final Map<String, Map<String, Object>> clientes = Map
    .of("100", Map.of(
        "id", "100",
        "nombre", "Pedro"
    ));

    public Map<String, Object> buscarPorId(String id){
        System.out.println("REPOSITORIO- buscar cliente: "+id);
        return clientes.get(id); //null
    }

}
