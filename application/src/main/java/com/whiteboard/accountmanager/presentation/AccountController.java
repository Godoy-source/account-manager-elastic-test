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
        log.info("Iniciando registro de nova conta");
        try {
            var dadosEntradaMapeados = AccountMapper.mapearDadosEntrada(dadosConta);
            ValidationUtils.validarCamposEntrada(dadosEntradaMapeados);

            var dadosContaDTO = AccountMapper.convertPresentationToDTO(dadosConta);
            var registro = accountService.saveDataAccount(dadosContaDTO, dadosEntradaMapeados);
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
            var busca = accountService.getUserAccount(usuarioId);
            var mapearDadosContaResposta = AccountMapper.toAccountMapper(busca);
            log.info("Finalizando busca conta por ID documento");
            return ResponseEntity.ok().body(mapearDadosContaResposta);
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }
    }

    @Override
    public ResponseEntity<List<AccountResponsePresentation>> findUserByFilter(FiltrosAccountRequestPresentation filtrosAccountRequestPresentation) {
        try {
            log.info("Iniciando busca de conta por filtros");
            var filtrosDTO = FiltroMapper.toFiltroMapper(filtrosAccountRequestPresentation);
            ValidationUtils.validarFiltros(filtrosDTO);

            var busca = accountService.searchAccounts(filtrosDTO);
            var mapearDadosContaResposta = AccountMapper.toAccountListMapper(busca);
            log.info("Fim busca de costas por filtros");
            return ResponseEntity.ok().body(mapearDadosContaResposta);
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }
    }
}
