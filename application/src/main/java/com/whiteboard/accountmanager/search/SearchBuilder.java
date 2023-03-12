package com.whiteboard.accountmanager.search;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.whiteboard.accountmanager.connections.elasticsearch.Connecting;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;

import java.io.IOException;

public class SearchBuilder {

    private static final String INDEX_CONTAS = "white-board-accounts";

    public static SearchResponse<ObjectNode> search(FiltrosRequestDTO filtroRequestDTO) throws IOException {
        var pesquisa = Connecting.getClient().search(s -> s
                        .index(INDEX_CONTAS)
                        .size(100)
                        .from(0)
                        .query(QueryBuilder.montarTipoBusca(filtroRequestDTO)),
                ObjectNode.class);
        return pesquisa;
    }

    public static ObjectNode get(String documentId) throws IOException {
        return Connecting.getClient().get(g -> g
                        .index(INDEX_CONTAS)
                        .id(documentId),
                ObjectNode.class
        ).source();
    }
}
