package com.whiteboard.accountmanager.presentation;


import com.codegen.rest.api.V1Api;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import com.whiteboard.accountmanager.presentation.exception.ExceptionHandler;
import com.whiteboard.accountmanager.service.AccountService;
import com.whiteboard.accountmanager.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class AccountController implements V1Api {

    private final AccountService accountService;
    @Override
    public ResponseEntity<NewAccountResponsePresentation> createNewUser(NewAccountRequestPresentation dadosConta) {
        try {
            log.info("Iniciando registro de nova conta");
            ValidationUtils.validarCampos(dadosConta);
            var registro = accountService.saveDataAccount(dadosConta);
            var mapearResposta = AccountMapper.toAccountMapper(registro);
            log.info("Fim registro de nova conta");
            return ResponseEntity.ok().body(mapearResposta);
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }
    }
}
