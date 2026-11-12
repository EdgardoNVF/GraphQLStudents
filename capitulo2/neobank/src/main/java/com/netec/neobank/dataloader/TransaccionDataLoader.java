package com.netec.neobank.dataloader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.MappedBatchLoader;

import com.netec.neobank.repository.TransaccionRepositorySimulado;
import com.netflix.graphql.dgs.DgsDataLoader;

@DgsDataLoader(name="TransaccionesPorCuenta", maxBatchSize = 2)
public class TransaccionDataLoader 
implements MappedBatchLoader<String, List<Map<String, Object>> >{

    private final TransaccionRepositorySimulado repositorio;

    public TransaccionDataLoader(TransaccionRepositorySimulado repositorySimulado){
        this.repositorio=repositorySimulado;
    }

    @Override
    public CompletionStage<Map<String, List<Map<String, Object>>>> load(Set<String> keys) {
        System.out.println("DATALOADER - Lote recibido "+keys);

        return CompletableFuture.completedFuture(repositorio.buscarPorCuentas(keys));
    }

}
