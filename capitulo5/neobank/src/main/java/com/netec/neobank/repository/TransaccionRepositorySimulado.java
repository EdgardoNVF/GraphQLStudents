package com.netec.neobank.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

@Repository 
public class TransaccionRepositorySimulado {
    private final Map<String, List<Map<String, Object>>> porCuenta =
    new ConcurrentHashMap<>();

    public TransaccionRepositorySimulado() {
        guardar("5001", Map.of("id", "T01", "amount", 500.0, "status", "COMPLETADA"));
        guardar("5001", Map.of("id", "T02", "amount", 200.0, "status", "PENDIENTE"));
        guardar("5002", Map.of("id", "T03", "amount", 1500.0, "status", "COMPLETADA"));
    }

    public void guardar(String cuentaNumero, Map<String, Object> transaccion) {
        porCuenta.computeIfAbsent(cuentaNumero, k -> new CopyOnWriteArrayList<>())
                 .add(transaccion);
    }


    //sin dataLoader
    public List<Map<String, Object>> buscarPorCuenta(String numero){
        System.out.println("REPOSITORIO - buscar transacciones de "+numero);
        return List.copyOf(porCuenta.getOrDefault(numero, List.of()));
    }

    //con dataLoader
    public Map<String, List<Map<String, Object>>> buscarPorCuentas(Set<String> numeros){
        System.out.println("REPOSITORIO- busqueda agrupada: "+numeros);
        Map<String, List<Map<String, Object>>> resultado = new LinkedHashMap<>();

        for(String numero:numeros){
            resultado.put(numero, List.copyOf(porCuenta.getOrDefault(numero, List.of())));
        }

        return resultado;
    }




}
