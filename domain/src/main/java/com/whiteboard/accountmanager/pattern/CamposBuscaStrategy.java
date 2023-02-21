package com.whiteboard.accountmanager.pattern;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.exceptions.ValidationException;

public interface CamposBuscaStrategy {
    void validar(FiltroDTO filtro) throws ValidationException;
}
