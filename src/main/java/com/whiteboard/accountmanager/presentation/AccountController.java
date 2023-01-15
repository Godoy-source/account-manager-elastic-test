package com.whiteboard.accountmanager.presentation;


import com.codegen.rest.api.ContaApi;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController extends ContaApi {

    @Override
    public NewAccountResponsePresentation newUserPost(@RequestBody NewAccountRequestPresentation dadosConta) {
        log.info("Iniciando registro de nova conta");
        try {
            var toAccountMapper = AccountMapper.toAccountMapper(dadosConta);

            return toAccountMapper;
        } catch (Exception e) {
            //return ResponseEntity.internalServerError().body(null);
            return null;
        }
    }

}
