package com.whiteboard.accountmanager.utils;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ValidationUtils {

    public void validarFiltros(List<FiltroDTO> filtros) throws ValidationException {
        for (var filtro : filtros) {
            CamposBuscaEnum.buscarEnumByCampo(filtro.getCorrelationEnumBusca().getCampo()).getRegra().validar(filtro);
        }
    }
}
