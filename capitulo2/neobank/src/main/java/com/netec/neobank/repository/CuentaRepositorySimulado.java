package com.netec.neobank.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository 
public class CuentaRepositorySimulado {
    private final List<Map<String,Object>> cuentas = List.of(
        Map.of("numero", "5001", "clienteId", "100", "saldo", 8000.0, "moneda", "MXN"),
        Map.of("numero", "5002", "clienteId", "100", "saldo", 3500.0, "moneda", "MXN"),
        Map.of("numero", "5003", "clienteId", "100", "saldo", 2500.0, "moneda", "MXN"),
        Map.of("numero", "5004", "clienteId", "100", "saldo", 6000.0, "moneda", "MXN"),
        Map.of("numero", "5005", "clienteId", "100", "saldo", 9500.0, "moneda", "MXN")
    );

    public List<Map<String, Object>> buscarPorClientId(String clientId){
        System.out.println("REPOSITORIO- Buscar cuentas del cliente "+clientId);
        return cuentas.stream()
                      .filter(t-> clientId.equals(t.get("clientId")))
                      .toList();

    }

    public boolean existeCuenta(String numero){
        return cuentas.stream().anyMatch(c->numero.equals(c.get("numero")));
    }


}
