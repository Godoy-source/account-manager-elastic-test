package com.whiteboard.accountmanager.pattern.impl;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import com.whiteboard.accountmanager.pattern.CamposBuscaStrategy;
import com.whiteboard.accountmanager.utils.ValidationCamposUtils;

public class CampoDataNascimentoRegraStrategy implements CamposBuscaStrategy {
    @Override
    public void validar(FiltroDTO filtro) throws ValidationException {
        ValidationCamposUtils.validarTamanho(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor(), 10, 10);
        ValidationCamposUtils.validarCharacters(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor());
    }
}
