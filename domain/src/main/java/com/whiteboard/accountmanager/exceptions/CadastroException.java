package com.whiteboard.accountmanager.exceptions;

import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import lombok.Getter;

@Getter
public class CadastroException extends Exception {
    private String codigo;
    private String descricaoCodigo;
    private CodigoErroEnum enumError;

    public CadastroException(CodigoErroEnum exceptionErrorEnum) {
        super(exceptionErrorEnum.getDescricaoCodigo());
        this.descricaoCodigo = exceptionErrorEnum.getDescricaoCodigo();
        this.codigo = exceptionErrorEnum.name();
        this.enumError = exceptionErrorEnum;
    }

    public CadastroException(CodigoErroEnum exceptionErrorEnum, String novaDescricao) {
        super(exceptionErrorEnum.getDescricaoCodigo());
        this.descricaoCodigo = novaDescricao;
        this.codigo = exceptionErrorEnum.name();
        this.enumError = exceptionErrorEnum;
    }
}
