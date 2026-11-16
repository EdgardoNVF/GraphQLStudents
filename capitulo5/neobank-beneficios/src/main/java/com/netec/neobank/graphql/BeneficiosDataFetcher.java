package com.netec.neobank.graphql;

import java.util.Map;

import com.netec.neobank.model.Cliente;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import com.netflix.graphql.dgs.DgsQuery;

@DgsComponent 
public class BeneficiosDataFetcher {

    //datos en memoria
    private static final Map<String, Integer> PUNTOS = Map.of(
        "100", 1200,
        "101",350
    );

    @DgsQuery 
    public String estadoBeneficios(){
         return "ACTIVO";
    }

    @DgsEntityFetcher (name="Cliente")
    public Cliente resolverCliente(Map<String, Object> referencia){
        String id= (String)referencia.get("id");
        System.out.println("FEDERATION - Entidad Cliente solicitada "+id);
        return new Cliente(id);
    }

    @DgsData(parentType = "Cliente", field = "puntos")
    public int puntos(DgsDataFetchingEnvironment dfe){
        Cliente cliente=dfe.getSource();
        int puntos = PUNTOS.getOrDefault(cliente.getId(), 0);

        System.out.println("BENEFICIOS - Cliente "+cliente.getId());

        return puntos;
    }








}
