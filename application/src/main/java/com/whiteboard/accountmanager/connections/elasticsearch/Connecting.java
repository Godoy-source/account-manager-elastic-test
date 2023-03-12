package com.whiteboard.accountmanager.connections.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Connecting {

    private static ElasticsearchClient client = new ElasticsearchClient(null);
    private static RestClientBuilder restClient = null;
    private static ElasticsearchTransport transport = null;

    @Bean
    public static void createClient() {
        log.info("Conectando com elastic");

        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "changeme"));

        restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider));

        transport = new RestClientTransport(
                restClient.build(), new JacksonJsonpMapper());
        client = new ElasticsearchClient(transport);
    }

    public static void closeClient() throws IOException {
        log.info("Finalizando conexão com elastic");
        restClient.build().close();
        transport.close();
    }
    public static final ElasticsearchClient getClient() {
        return client;
    }

}
