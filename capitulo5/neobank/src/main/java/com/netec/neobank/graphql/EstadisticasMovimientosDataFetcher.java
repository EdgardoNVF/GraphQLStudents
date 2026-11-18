package com.netec.neobank.graphql;

import java.util.Map;

import com.netec.neobank.service.EstadisticasMovimientosService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;

@DgsComponent 
public class EstadisticasMovimientosDataFetcher {

    private final EstadisticasMovimientosService estadisticas;

    public EstadisticasMovimientosDataFetcher(EstadisticasMovimientosService estadisticas){
        this.estadisticas=estadisticas;
    }

    @DgsData(parentType = "Cuenta", field = "estadisticasMovimientos")
    public Map<String, Object> estadisticasMovimientos(
        DgsDataFetchingEnvironment dfe
    ){
        Map<String, Object> cuenta=dfe.getSource();
        String numero= (String)cuenta.get("numero");

        return  estadisticas.calcular(numero);
    }

}
