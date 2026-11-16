package com.netec.neobank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netec.neobank.entity.TransaccionEntity;
import com.netec.neobank.repository.TransaccionJpaRepository;



@Service 
public class TransaccionService {
    private final TransaccionJpaRepository transacciones;

    public TransaccionService(TransaccionJpaRepository transacciones) {
        this.transacciones = transacciones;
    }

    @Transactional (readOnly = true)
    public Map<String, List<Map<String, Object>>> buscarPorCuentas(
            Set<String> numeros) {
                
        System.out.println("JPA - Lote solicitado: " + numeros);
        Map<String, List<Map<String, Object>>> resultado = new HashMap<>();

        for (String numero : numeros) {
            resultado.put(numero, new ArrayList<>());
        }
        if (numeros.isEmpty()) {
            return resultado;
        }

        List<TransaccionEntity> encontradas =
            transacciones.findByCuenta_NumeroIn(numeros);
        System.out.println("JPA - Movimientos encontrados: " + encontradas.size());

        for (TransaccionEntity t : encontradas) {
            String numero = t.getCuenta().getNumero();
            resultado.get(numero).add(aGraphQL(t));
        }
        return resultado;
    }

    private Map<String, Object> aGraphQL(TransaccionEntity t) {
        return Map.of(
            "id", t.getId(),
            "amount", t.getAmount().doubleValue(),
            "status", t.getStatus()
        );
    }

    
    
}
