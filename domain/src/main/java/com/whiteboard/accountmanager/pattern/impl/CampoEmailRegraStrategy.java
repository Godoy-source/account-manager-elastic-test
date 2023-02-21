package com.whiteboard.accountmanager.pattern.impl;

import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import com.whiteboard.accountmanager.pattern.CamposBuscaStrategy;
import com.whiteboard.accountmanager.utils.ValidationCamposUtils;

public class CampoEmailRegraStrategy implements CamposBuscaStrategy {

    @Override
    public void validar(String campo, String valor) throws ValidationException {
        ValidationCamposUtils.validarTamanho(campo, valor, 5, 100);
        validarCaracterNecessario(valor);
        ValidationCamposUtils.validarCharacters(campo, valor);
    }

    private void validarCaracterNecessario(String valor) throws ValidationException {
        if (!valor.contains("@")) {
            throw new ValidationException(CodigoErroEnum.EROO_DADOS_ENTRADA_CARACTER_NECESSARIO);
        }
    }
}
