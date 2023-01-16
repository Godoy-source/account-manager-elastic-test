package com.whiteboard.accountmanager.presentation;


import com.codegen.rest.api.V1Api;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import com.whiteboard.accountmanager.presentation.exception.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController implements V1Api {

    @Override
    public ResponseEntity<NewAccountResponsePresentation> createNewUser(NewAccountRequestPresentation dadosConta) {
        log.info("Iniciando registro de nova conta");
        try {
            var toAccountMapper = AccountMapper.toAccountMapper(dadosConta);

            log.info("Fim registro de nova conta");
            return ResponseEntity.ok().body(toAccountMapper);
        } catch (Exception e) {
            return ExceptionHandler.handle(e);
        }
    }

}
