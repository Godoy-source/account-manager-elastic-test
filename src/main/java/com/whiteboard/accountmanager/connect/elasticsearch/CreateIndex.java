package com.whiteboard.accountmanager.connect.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
/*
Classe de apoio para criação de novos index
 */
public class CreateIndex {

    private static DocTransport transport = new DocTransport();

    public static void builders() throws Exception {
        ElasticsearchClient client = new ElasticsearchClient(transport);


        CreateIndexResponse createResponse = client.indices().create(
                new CreateIndexRequest.Builder()
                        .index("WhiteBoard")
                        .aliases("foo",
                                new Alias.Builder().isWriteIndex(true).build())
                        .build()
        );

        System.out.println(createResponse);
    }
}
