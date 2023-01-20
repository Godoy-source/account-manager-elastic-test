package com.whiteboard.accountmanager.presentation.exception;

import com.codegen.rest.model.ErroNegocioPresentation;
import com.codegen.rest.model.ErroPresentation;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroNaoEfetivadoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ExceptionHandler {
    public static ResponseEntity handle(Exception e) {
        if (e instanceof CadastroNaoEfetivadoException) {
            return new ResponseEntity<>(new ErroNegocioPresentation()
                    .code(((CadastroNaoEfetivadoException) e).getCodigo())
                    .message(((CadastroNaoEfetivadoException) e).getDescricaoCodigo()),
                    HttpStatusCode.valueOf(((CadastroNaoEfetivadoException) e).getEnumError().getCodigoHttp()));
        }
        log.error("Ocorreu um erro não mapeado. Erro {}", e);
        return new ResponseEntity<>(new ErroPresentation()
                .code(CodigoErroEnum.ERRO_INTERO.getCodigoHttp())
                .correlationError(CodigoErroEnum.ERRO_INTERO.getDescricaoCodigo())
                .message(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
