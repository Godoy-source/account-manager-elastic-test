package com.whiteboard.accountmanager.mapper;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhraseQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.util.ObjectBuilder;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryBuilder {
    public static List<Query> montarFiltros(HashMap<String, String> dadosEntradaMapeados, List<CamposBuscaEnum> filtros) {
        List<Query> queryFiltros = new ArrayList<>();
        for (var filtro : filtros) {
            if (filtro.getCampo().equals(AccountMapper.getKeyOfMap(dadosEntradaMapeados, filtro.getCampo()))) {
                queryFiltros.add(montarQuery(filtro.getCampo(), dadosEntradaMapeados.get(filtro.getCampo())));
            }
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
