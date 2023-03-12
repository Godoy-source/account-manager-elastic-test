package com.whiteboard.accountmanager.search;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhraseQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.enums.TipoBuscaEnum;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    public static Query montarTipoBusca(FiltrosRequestDTO filtroRequestDTO) {
        if (filtroRequestDTO.getTipoBusca().equals(TipoBuscaEnum.FILTRAR)) {
            return BoolQuery.of(builder -> builder.filter(QueryBuilder.montarFiltros(filtroRequestDTO.getFiltros())))._toQuery();
        } else {
            return BoolQuery.of(builder -> builder.should(QueryBuilder.montarFiltros(filtroRequestDTO.getFiltros())))._toQuery();
        }
    }

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
