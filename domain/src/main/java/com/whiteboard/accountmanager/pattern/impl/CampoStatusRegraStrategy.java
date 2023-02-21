package com.whiteboard.accountmanager.pattern.impl;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.enums.StatusEnum;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import com.whiteboard.accountmanager.pattern.CamposBuscaStrategy;
import com.whiteboard.accountmanager.utils.ValidationCamposUtils;

public class CampoStatusRegraStrategy implements CamposBuscaStrategy {

    @Override
    public void validar(FiltroDTO filtro) throws ValidationException {
        ValidationCamposUtils.validarValorExiste(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor());
        StatusEnum.buscarEnumByStatus(filtro.getValor()).getStatus();
    }
}
