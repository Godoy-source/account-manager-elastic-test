package com.whiteboard.accountmanager.repository;

import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.AccountDTO;
import com.whiteboard.accountmanager.configuration.AppConnections;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroNaoEfetivadoException;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Repository
public class AccountManagerRepositoryImpl implements AccountManagerRepository {

    private final RestTemplate template;
    private final AppConnections connections;

    public AccountManagerRepositoryImpl(RestTemplate template, AppConnections conections) {
        this.template = template;
        this.connections = conections;
    }

    @Override
    public NewAccountResponsePresentation createAccount(AccountDTO dadosConta) throws CadastroNaoEfetivadoException {
        var toSaveAccountMapper = AccountMapper.toAccountMapper(dadosConta);
        var entity = new HttpEntity<>(toSaveAccountMapper);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(connections.getLogStash());
        var response = template.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        if(ObjectUtils.isEmpty(response) || !response.getStatusCode().is2xxSuccessful()) {
            log.error("Erro ao cadastrar conta no ElasticSearch");
            throw new CadastroNaoEfetivadoException(CodigoErroEnum.ERRO_CADASTRO_NAO_EFETIVADO);
        }
        return toSaveAccountMapper;
    }
}
