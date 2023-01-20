package com.whiteboard.accountmanager.repository;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.AccountDTO;
import com.whiteboard.accountmanager.configuration.AppConnections;
import com.whiteboard.accountmanager.connect.elasticsearch.Connecting;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroNaoEfetivadoException;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Repository
public class AccountManagerRepositoryImpl implements AccountManagerRepository {

    private final RestTemplate template;
    private final AppConnections connections;

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
    public NewAccountResponsePresentation createAccount(AccountDTO dadosConta) throws CadastroNaoEfetivadoException {
        var toSaveAccountMapper = AccountMapper.toAccountMapper(dadosConta);
        try {
            log.info("Iniciando registro de conta");
            IndexRequest<NewAccountResponsePresentation> request = IndexRequest.of(i -> i
                    .index("white-board")
                    .id(dadosConta.getId().toString())
                    .document(toSaveAccountMapper));
            Connecting.getClient().index(request);
            log.info("Nova conta registrada, id: {}", toSaveAccountMapper.getId());
        } catch (IOException e) {
            log.error("Erro ao registrar conta. Erro: {}", e.getLocalizedMessage());
            throw new CadastroNaoEfetivadoException(CodigoErroEnum.ERRO_CADASTRO_NAO_EFETIVADO);
        }
        return toSaveAccountMapper;
    }
}
