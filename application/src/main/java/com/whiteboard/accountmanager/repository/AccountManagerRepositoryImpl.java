package com.whiteboard.accountmanager.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.*;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.whiteboard.accountmanager.configuration.AppConnections;
import com.whiteboard.accountmanager.connections.elasticsearch.Connecting;
import com.whiteboard.accountmanager.connections.elasticsearch.DocTransport;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.mapper.QueryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Response;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class AccountManagerRepositoryImpl implements AccountManagerRepository {

    private final RestTemplate template;
    private final AppConnections connections;

    private final DocTransport transport = new DocTransport();

    public AccountManagerRepositoryImpl(RestTemplate template, AppConnections conections) {
        this.template = template;
        this.connections = conections;
    }

    private void logStashCall() {
        //var entity = new HttpEntity<>(toSaveAccountMapper);
        //UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(connections.getLogStash());
        //var response = template.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        /*(if(ObjectUtils.isEmpty(response) || !response.getStatusCode().is2xxSuccessful()) {
            log.error("Erro ao cadastrar conta no ElasticSearch");
            throw new CadastroNaoEfetivadoException(CodigoErroEnum.ERRO_CADASTRO_NAO_EFETIVADO);
        }*/
    }

    @Override
    public AccountDTO createAccount(AccountDTO dadosConta) throws CadastroException {
        try {
            log.info("Chamando a base para registrar nova conta");
            IndexRequest<AccountDTO> request = IndexRequest.of(i -> i
                    .index("white-board")
                    .id(dadosConta.getCpf())
                    .document(dadosConta));
            Connecting.getClient().index(request);
            log.info("Nova conta registrada, id: {}", dadosConta.getId());
        } catch (IOException e) {
            log.error("Erro ao registrar conta. Erro: {}", e.getLocalizedMessage());
            throw new CadastroException(CodigoErroEnum.ERRO_CADASTRO_NAO_EFETIVADO);
        }
        return dadosConta;
    }

    @Override
    public AccountDTO getAccount(String usuarioId) throws IOException {
        log.info("Inicio consulta dados da conta {} no Elastic", usuarioId);

        var response = Connecting.getClient().get(g -> g
                        .index("white-board")
                        .id(usuarioId),
                ObjectNode.class
        );
        if (response.found()) {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(response.source().toString());
            AccountDTO emp = gson.fromJson(object, AccountDTO.class);
            log.info("Conta encontrada. Fim de consulta de dados");
            return emp;
        } else {
            throw new IOException(CodigoErroEnum.ERRO_INTERO.getDescricaoCodigo());
        }
    }

    public AccountDTO findAccountByFilter(String usuarioId) throws IOException {

        var response = Connecting.getClient().search(s -> s
                        .index("white-board")
                        .query(QueryBuilder.addQuery(usuarioId)),
                ObjectNode.class);
        return null;
    }

    public boolean verifyDocExists(String usuarioId) throws IOException {
        try {
            return Connecting.getClient().exists(builder -> builder
                    .index("white-board")
                    .id(usuarioId)
                    .storedFields("_none_")).value();
        } catch (Exception e) {
            throw new IOException(CodigoErroEnum.ERRO_INTERO.getDescricaoCodigo());
        }
    }

}
