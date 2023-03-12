package com.whiteboard.accountmanager.presentation;


import com.codegen.rest.api.V1Api;
import com.codegen.rest.model.AccountResponsePresentation;
import com.codegen.rest.model.FiltrosAccountRequestPresentation;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import com.whiteboard.accountmanager.mapper.FiltroMapper;
import com.whiteboard.accountmanager.presentation.exception.ExceptionHandler;
import com.whiteboard.accountmanager.service.AccountService;
import com.whiteboard.accountmanager.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class AccountController implements V1Api {

    private final AccountService accountService;

    @Override
    public ResponseEntity<AccountResponsePresentation> createNewUser(NewAccountRequestPresentation dadosConta) {
        try {
            log.info("Iniciando registro de nova conta");
            var dadosEntradaMapeados = AccountMapper.mapearDadosEntrada(dadosConta);
            var convertMapToFiltro = AccountMapper.mapDadosEntradaToFiltrosDTO(dadosEntradaMapeados);
            ValidationUtils.validarFiltros(convertMapToFiltro);

            var dadosContaDTO = AccountMapper.convertPresentationToDTO(dadosConta);
            var pesquisarPor = FiltroMapper.chavesPrincipais(convertMapToFiltro);
            var registro = accountService.saveDataAccount(dadosContaDTO, pesquisarPor);
            var mapearDadosContaResposta = AccountMapper.toAccountMapper(registro);
            log.info("Fim registro de nova conta");
            return ResponseEntity.ok().body(mapearDadosContaResposta);
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }
    }

    @Override
    public ResponseEntity<AccountResponsePresentation> findUserByID(String usuarioId) {
        try {
            log.info("Iniciando busca conta por ID documento");
            var busca = accountService.getUserAccountById(usuarioId);
            var mapearDadosContaResposta = AccountMapper.toAccountMapper(busca);
            log.info("Finalizando busca conta por ID documento");
            return ResponseEntity.ok().body(mapearDadosContaResposta);
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }
    }

    @Override
    public ResponseEntity<List<AccountResponsePresentation>> findUserByFilter(Integer pagina, Integer tamanhoPagina, FiltrosAccountRequestPresentation filtrosAccountRequestPresentation) {
        try {
            log.info("Iniciando busca de conta por filtros");
            var filtroRequestDTO = FiltroMapper.toFiltroRequestMapper(pagina, tamanhoPagina, filtrosAccountRequestPresentation);
            ValidationUtils.validarFiltros(filtroRequestDTO.getFiltros());

            var busca = accountService.searchAccounts(filtroRequestDTO);
            var mapearDadosContaResposta = AccountMapper.toAccountListMapper(busca);
            log.info("Fim busca de costas por filtros");
            return ResponseEntity.ok().body(mapearDadosContaResposta);
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }
    }
}
