package com.whiteboard.accountmanager.exceptions;

import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import lombok.Getter;

@Getter
public class ValidationException extends Exception {
    private Integer codigo;
    private String descricaoCodigo;
    private CodigoErroEnum enumError;

    public ValidationException(CodigoErroEnum exceptionErrorEnum) {
        super(exceptionErrorEnum.getDescricaoCodigo());
        this.descricaoCodigo = exceptionErrorEnum.getDescricaoCodigo();
        this.codigo = exceptionErrorEnum.getCodigoHttp();
        this.enumError = exceptionErrorEnum;
    }

    public ValidationException(CodigoErroEnum exceptionErrorEnum, String novaDescricao) {
        super(novaDescricao);
        this.descricaoCodigo = novaDescricao;
        this.codigo = exceptionErrorEnum.getCodigoHttp();
        this.enumError = exceptionErrorEnum;
    }
}
