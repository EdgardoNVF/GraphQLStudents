package com.netec.neobank.dataloader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.MappedBatchLoader;

import com.netec.neobank.service.TransaccionService;
import com.netflix.graphql.dgs.DgsDataLoader;

@DgsDataLoader(name="TransaccionesPorCuenta", maxBatchSize = 2)
public class TransaccionDataLoader 
implements MappedBatchLoader<String, List<Map<String, Object>> >{

    private final TransaccionService service;

    public TransaccionDataLoader(TransaccionService service){
            this.service=service;
    }
    

    @Override
    public CompletionStage<Map<String, List<Map<String, Object>>>> load(Set<String> keys) {
        System.out.println("DATALOADER - Lote recibido "+keys);

        return CompletableFuture.completedFuture(service.buscarPorCuentas(keys));
    }

}
