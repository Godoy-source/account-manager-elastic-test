package com.whiteboard.accountmanager.mapper;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

public class QueryBuilder {
    public static Query addQuery(String usuarioId) {
        Query byName = MatchQuery.of(m -> m
                .field("cpf")
                .query(usuarioId)
        )._toQuery();
        return byName;
    }
}
