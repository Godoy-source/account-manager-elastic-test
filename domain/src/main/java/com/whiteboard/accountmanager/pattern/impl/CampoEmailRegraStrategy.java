package com.whiteboard.accountmanager.pattern.impl;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import com.whiteboard.accountmanager.pattern.CamposBuscaStrategy;
import com.whiteboard.accountmanager.utils.ValidationCamposUtils;

public class CampoEmailRegraStrategy implements CamposBuscaStrategy {

    @Override
    public void validar(FiltroDTO filtro) throws ValidationException {
        ValidationCamposUtils.validarValorExiste(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor());
        ValidationCamposUtils.validarTamanho(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor(), 5, 100);
        validarCaracterNecessario(filtro.getValor());
        ValidationCamposUtils.validarCharacters(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor());
    }

    private void validarCaracterNecessario(String valor) throws ValidationException {
        if (!valor.contains("@")) {
            throw new ValidationException(CodigoErroEnum.EROO_DADOS_ENTRADA_CARACTER_NECESSARIO);
        }
    }
}
