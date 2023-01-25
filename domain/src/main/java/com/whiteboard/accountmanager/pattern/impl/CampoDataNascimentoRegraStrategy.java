package com.whiteboard.accountmanager.pattern.impl;

import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.pattern.CamposBuscaStrategy;
import com.whiteboard.accountmanager.utils.ValidationCamposUtils;

public class CampoDataNascimentoRegraStrategy implements CamposBuscaStrategy {
    @Override
    public void validar(String campo, String valor) throws CadastroException {
        ValidationCamposUtils.validarTamanho(campo,valor, 10, 10);
        ValidationCamposUtils.validarCharacters(campo, valor);
    }
}
