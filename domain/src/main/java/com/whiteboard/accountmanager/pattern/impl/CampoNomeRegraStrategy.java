package com.whiteboard.accountmanager.pattern.impl;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import com.whiteboard.accountmanager.pattern.CamposBuscaStrategy;
import com.whiteboard.accountmanager.utils.ValidationCamposUtils;

public class CampoNomeRegraStrategy implements CamposBuscaStrategy {

    @Override
    public void validar(FiltroDTO filtro) throws ValidationException {
        ValidationCamposUtils.validarValorExiste(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor());
        ValidationCamposUtils.validarTamanho(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor(), 4, 255);
        ValidationCamposUtils.validarCharacters(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor());
    }
}
