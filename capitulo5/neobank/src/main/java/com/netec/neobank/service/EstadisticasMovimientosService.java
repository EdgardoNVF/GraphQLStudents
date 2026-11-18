package com.netec.neobank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netec.neobank.entity.TransaccionEntity;
import com.netec.neobank.repository.TransaccionJpaRepository;

@Service 
public class EstadisticasMovimientosService {
    private final TransaccionJpaRepository transacciones;

    public EstadisticasMovimientosService(TransaccionJpaRepository transacciones){
        this.transacciones=transacciones;
    }

    @Cacheable (cacheNames = "estadisticasMovimientos", key = "#p0")
    @Transactional(readOnly = true)
    public Map<String, Object> calcular(String numeroCuenta){
        System.out.println("CACHE MISS - calculando cuenta "+numeroCuenta); 

        List<TransaccionEntity> movimientos = transacciones.
                                findByCuenta_NumeroIn(List.of(numeroCuenta));
        
        BigDecimal total=BigDecimal.ZERO;

        for(TransaccionEntity tmp:movimientos){
             total=total.add(tmp.getAmount());
        }

        return Map.of(
            "cantidad", movimientos.size(),
            "montoTotal", total.doubleValue()
        );
    }


}
