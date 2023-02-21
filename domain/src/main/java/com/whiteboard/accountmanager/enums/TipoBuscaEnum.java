package com.whiteboard.accountmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoBuscaEnum {
    FILTRAR("filtrar"),

    AGREGAR("agregar");

    private final String tipo;

    public static TipoBuscaEnum buscarEnumByTipo(String tipo) {
        return Arrays.stream(TipoBuscaEnum.values())
                .filter(t -> t.getTipo().equals(tipo))
                .findFirst()
                .orElseThrow();
    }
}
