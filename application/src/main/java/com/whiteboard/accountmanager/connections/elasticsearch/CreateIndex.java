package com.whiteboard.accountmanager.connections.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;

/*
Classe de apoio para criação de novos index
 */
public class CreateIndex {

    private static DocTransport transport = new DocTransport();

    public static void builders() throws Exception {
        ElasticsearchClient client = new ElasticsearchClient(transport);
        CreateIndexRequest request = new CreateIndexRequest.Builder()
                .index("white-board")
                .build();
        
        CreateIndexResponse createResponse = client.indices().create(request);

        System.out.println(createResponse);
    }
}
