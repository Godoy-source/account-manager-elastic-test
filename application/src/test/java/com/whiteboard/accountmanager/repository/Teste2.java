/*package com.whiteboard.accountmanager.repository;

import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.google.gson.Gson;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.exception.CadastroException;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.repository.AccountManagerRepositoryImpl;
import com.whiteboard.accountmanager.search.SearchBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Teste2 {
    private AccountManagerRepositoryImpl accountService;
    private SearchBuilder searchBuilder;

    @BeforeEach
    public void setUp() {
        searchBuilder = Mockito.mock(SearchBuilder.class);
    }

    @Test
    public void findAccountByFilter_shouldReturnListOfAccounts() throws CadastroException, IOException {
        var gson = new Gson();
        var filtroRequestDTO = FiltrosRequestDTO.builder()
                .filtros(List.of(FiltroDTO.builder()
                        .correlationEnumBusca(CamposBuscaEnum.CAMPO_NOME)
                        .valor("Fulano")
                        .build()))
                .build();

        List<AccountDTO> expectedAccounts = List.of(AccountDTO.builder().build());

        HitsMetadata hit = Mockito.mock(HitsMetadata.class);
        Mockito.when(hit.hits()).thenReturn(gson.toJson(expectedAccounts.get(0)));

        SearchHits searchHits = Mockito.mock(SearchHits.class);
        Mockito.when(searchHits.getHits()).thenReturn(new SearchHit[] {hit});

        SearchResponse searchResponse = Mockito.mock(SearchResponse.class);
        Mockito.when(searchResponse.getHits()).thenReturn(searchHits);

        Mockito.when(searchBuilder.search(Mockito.any(SearchSourceBuilder.class))).thenReturn(searchResponse);

        List<AccountDTO> actualAccounts = accountService.searchAccounts(filtroRequestDTO);

        Assertions.assertEquals(expectedAccounts.size(), actualAccounts.size());
        Assertions.assertEquals(expectedAccounts.get(0), actualAccounts.get(0));

        Mockito.verify(searchBuilder, Mockito.times(1)).search(Mockito.any(SearchSourceBuilder.class));
    }
}*/
