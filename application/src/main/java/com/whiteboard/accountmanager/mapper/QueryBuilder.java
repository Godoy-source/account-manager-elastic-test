package com.whiteboard.accountmanager.mapper;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhraseQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.whiteboard.accountmanager.dto.FiltroDTO;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    public static List<Query> montarFiltros(List<FiltroDTO> filtros) {
        List<Query> queryFiltros = new ArrayList<>();
        for (var filtro : filtros) {
            queryFiltros.add(montarQuery(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor()));
        }
        return queryFiltros;
    }

    public static Query montarQuery(String campo, String value) {
        return MatchPhraseQuery.of(m -> m
                .field(campo)
                .query(value)
        )._toQuery();
    }


}
