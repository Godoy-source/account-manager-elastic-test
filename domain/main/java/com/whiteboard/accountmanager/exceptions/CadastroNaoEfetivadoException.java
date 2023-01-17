package com.whiteboard.accountmanager.exceptions;

import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import lombok.Getter;

@Getter
public class CadastroNaoEfetivadoException extends Exception {
    private String codigo;
    private String descricaoCodigo;
    private CodigoErroEnum enumError;

    public CadastroNaoEfetivadoException(CodigoErroEnum exceptionErrorEnum) {
        super(exceptionErrorEnum.getDescricaoCodigo());
        this.descricaoCodigo = exceptionErrorEnum.getDescricaoCodigo();
        this.codigo = exceptionErrorEnum.name();
        this.enumError = exceptionErrorEnum;
    }

    public CadastroNaoEfetivadoException(CodigoErroEnum exceptionErrorEnum, String novaDescricao) {
        super(exceptionErrorEnum.getDescricaoCodigo());
        this.descricaoCodigo = novaDescricao;
        this.codigo = exceptionErrorEnum.name();
        this.enumError = exceptionErrorEnum;
    }
}
