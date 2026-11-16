package com.netec.neobank.graphql;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;

import com.netec.neobank.service.ClienteService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent 
public class NeoBankDataFetcher {

    private final ClienteService clientes;

    public NeoBankDataFetcher(ClienteService clientes){
        this.clientes=clientes;
    }
    

    @DgsQuery //Query.cliente
    public Map<String,Object> cliente(@InputArgument("id") String id){
        System.out.println("DATAFETCHER - Query.cliente: "+id);
        return clientes.buscarPorId(id);
    }

    @DgsData(parentType = "Cliente", field = "cuentas")
    public List<Map<String, Object>> cuentas(DgsDataFetchingEnvironment dfe){
        Map<String, Object> cliente =  dfe.getSource();
        String id = (String)cliente.get("id");
        System.out.println("DATAFETCHER - Cliente.cuentas de "+id);
        return clientes.buscarCuentas(id);
    }


    @DgsData(parentType = "Cuenta", field = "transacciones")
    public CompletableFuture<List<Map<String, Object>>> transacciones(DgsDataFetchingEnvironment dfe){
        Map<String , Object> cuenta = dfe.getSource();
        String numero= (String)cuenta.get("numero");

        DataLoader<String, List<Map<String, Object>>> loader=
        dfe.getDataLoader("TransaccionesPorCuenta");

        return loader.load(numero);
    }

}
