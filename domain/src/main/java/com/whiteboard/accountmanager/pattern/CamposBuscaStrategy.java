package com.whiteboard.accountmanager.pattern;

import com.whiteboard.accountmanager.exceptions.CadastroException;

import java.util.Map;

public interface CamposBuscaStrategy {
    void validar(String campo, String valor) throws CadastroException;
}
