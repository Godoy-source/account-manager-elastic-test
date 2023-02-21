package com.whiteboard.accountmanager.pattern;

import com.whiteboard.accountmanager.exceptions.ValidationException;

public interface CamposBuscaStrategy {
    void validar(String campo, String valor) throws ValidationException;
}
