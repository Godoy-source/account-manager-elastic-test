/*package com.whiteboard.accountmanager.repository;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.EnderecoDTO;
import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.search.SearchBuilder;
import com.whiteboard.accountmanager.service.AccountService;
import com.whiteboard.accountmanager.utils.DataUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountManagerRepositoryImplTest {

    @Mock
    private AccountManagerRepositoryImpl accountManagerRepository;

    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        accountManagerRepository = mock(AccountManagerRepositoryImpl.class);
        accountService = new AccountService(accountManagerRepository);
    }

    public static AccountDTO mockConta() {
        return AccountDTO.builder()
                .id("3123")
                .documento_id("id.toString()")
                .nome("dadosConta.getNome()")
                .cpf("00000000000")
                .email("dadosConta.getEmail()@gmail.com")
                .endereco(EnderecoDTO.builder()
                        .cidade("dadosConta.getEndereco().getCidade()")
                        .rua("Rua")
                        .cep("dadosConta.getEndereco().getCep()")
                        .estado("dadosConta.getEndereco().getEstado()")
                        .build())
                .dataNascimento("21-02-2023")
                .status("A")
                .dataInclusao(DataUtils.formatOffsetData(OffsetDateTime.now()))
                .build();
    }

    @Test
    public void a() throws CadastroException {
        var filtroRequestDTO = FiltrosRequestDTO.builder()
                .filtros(List.of(FiltroDTO.builder()
                        .correlationEnumBusca(CamposBuscaEnum.CAMPO_NOME)
                        .valor("Fulano")
                        .build()))
                .build();
        List<AccountDTO> actualAccounts = List.of(mockConta());
        when(accountService.searchAccounts(filtroRequestDTO)).thenReturn(actualAccounts);
    }

    @Test
    public void findAccountByFilter_shouldReturnListOfAccounts() throws CadastroException, IOException {
        var gson = new Gson();
        SearchBuilder searchBuilder = Mockito.mock(SearchBuilder.class);
        var filtroRequestDTO = FiltrosRequestDTO.builder()
                .filtros(List.of(FiltroDTO.builder()
                        .correlationEnumBusca(CamposBuscaEnum.CAMPO_NOME)
                        .valor("Fulano")
                        .build()))
                .build();

        List<AccountDTO> expectedAccounts = List.of(AccountDTO.builder().build());

        //Hit<ObjectNode> hit = Mockito.mock(Hit.class);
        HitsMetadata searchHits = Mockito.mock(HitsMetadata.class);
        //Mockito.when(hit.source()).thenReturn(new HitsMetadata[] {new HitsMetadata(0, "_doc", "1").source(expectedAccounts.get(0).toString())});
        Mockito.when(searchHits.hits()).thenReturn(Arrays.asList(new HitsMetadata[0]));
        //HitsMetadata hits = Mockito.mock(HitsMetadata.class);
        //Mockito.when(hits.hits()).thenReturn(List.of(hit));

        SearchResponse searchResponse = Mockito.mock(SearchResponse.class);
        Mockito.when(searchResponse.hits()).thenReturn(searchHits);

        Mockito.when(searchBuilder.search(filtroRequestDTO)).thenReturn(searchResponse);

        List<AccountDTO> actualAccounts = accountService.searchAccounts(filtroRequestDTO);

        Assertions.assertEquals(expectedAccounts.size(), actualAccounts.size());
        Assertions.assertEquals(expectedAccounts.get(0), actualAccounts.get(0));

        //Mockito.verify(searchBuilder, times(1)).search(filtroRequestDTO);
    }

    /*@Test
    public void findAccountByFilter_shouldReturnListOfAccounts() throws CadastroException, IOException {
        var gson = new Gson();
        var filtroRequestDTO = FiltrosRequestDTO.builder()
                .filtros(List.of(FiltroDTO.builder()
                        .correlationEnumBusca(CamposBuscaEnum.CAMPO_NOME)
                        .valor("Fulano")
                        .build()))
                .build();

        List<AccountDTO> expectedAccounts = List.of(AccountDTO.builder()
                .build());

        SearchHit[] searchHits = expectedAccounts.stream()
                .map(account -> new SearchHit(1L, "index", "_doc", null,
                        null, gson.toJson(account), null, null, null))
                .toArray(SearchHit[]::new);

        SearchHits hits = new SearchHits(searchHits, new TotalHits(1, TotalHits.Relation.EQUAL_TO), 1.0f);
        SearchResponse searchResponse = new SearchResponse(hits, new Aggregations(Collections.emptyList()), null,
                SearchResponse.Clusters.EMPTY, null, 1, 1, 0, 0, 0, 0, 0, false);

        Mockito.when(searchBuilder.search(filtroRequestDTO)).thenReturn(searchResponse);

        List<AccountDTO> actualAccounts = accountService.searchAccounts(filtroRequestDTO);

        Assertions.assertEquals(expectedAccounts.size(), actualAccounts.size());
        Assertions.assertEquals(expectedAccounts.get(0), actualAccounts.get(0));

        Mockito.verify(searchBuilder, times(1)).search(filtroRequestDTO);
    }*/

    /*@Test(expected = CadastroException.class)
    public void findAccountByFilter_shouldThrowCadastroException() throws CadastroException, IOException {
        FiltrosRequestDTO filtroRequestDTO = new FiltrosRequestDTO();
        filtroRequestDTO.setNome("Fulano");

        when(searchBuilder.search(filtroRequestDTO)).thenThrow(new IOException());

        accountService.findAccountByFilter(filtroRequestDTO);
    }*/

