package com.whiteboard.accountmanager.repository;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.configuration.AppConnections;
import com.whiteboard.accountmanager.connections.elasticsearch.Connecting;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroNaoEfetivadoException;
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
    public AccountDTO createAccount(AccountDTO dadosConta) throws CadastroNaoEfetivadoException {
        try {
            log.info("Chamando a base para registrar nova conta");
            IndexRequest<AccountDTO> request = IndexRequest.of(i -> i
                    .index("white-board")
                    .document(dadosConta));
            Connecting.getClient().index(request);
            log.info("Nova conta registrada, id: {}", dadosConta.getId());
        } catch (IOException e) {
            log.error("Erro ao registrar conta. Erro: {}", e.getLocalizedMessage());
            throw new CadastroNaoEfetivadoException(CodigoErroEnum.ERRO_CADASTRO_NAO_EFETIVADO);
        }
        return dadosConta;
    }
}
