package com.whiteboard.accountmanager.search;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.whiteboard.accountmanager.configuration.ApplicationProperties;
import com.whiteboard.accountmanager.connections.elasticsearch.Connecting;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.utils.Contants;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
public class SearchBuilder {

    private final ApplicationProperties properties;

    public SearchResponse<ObjectNode> search(FiltrosRequestDTO filtroRequestDTO) throws IOException {
        var tamanhoPagina = definirTamanhoPaginacao(filtroRequestDTO.getTamanhoPagina());
        var pesquisa = Connecting.getClient().search(s -> s
                        .index(properties.getIndiceAccounts())
                        .size(tamanhoPagina)
                        .from(definirPagina(filtroRequestDTO.getPagina()) * tamanhoPagina)
                        .query(QueryBuilder.montarTipoBusca(filtroRequestDTO)),
                ObjectNode.class);
        return pesquisa;
    }

    public ObjectNode get(String documentId) throws IOException {
        return Connecting.getClient().get(g -> g
                        .index(properties.getIndiceAccounts())
                        .id(documentId),
                ObjectNode.class
        ).source();
    }

    private Integer definirTamanhoPaginacao(Integer tamanhoPagina) {
        return ObjectUtils.isEmpty(tamanhoPagina) ? Contants.TAMANHO_PAGINACAO_DEFAULT : tamanhoPagina;
    }

    private Integer definirPagina(Integer pagina) {
        return ObjectUtils.isEmpty(pagina) ? Contants.PAGINA_DEFAULT : pagina;
    }
}
