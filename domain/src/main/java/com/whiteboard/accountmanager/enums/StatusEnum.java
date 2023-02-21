package com.whiteboard.accountmanager.enums;

import com.whiteboard.accountmanager.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    A("A", "Ativo"),

    B("B", "Bloqueado"),

    D("D", "Desligado");

    private final String status;
    private final String descricao;

    public static StatusEnum buscarEnumByStatus(String status) throws ValidationException {
        return Arrays.stream(StatusEnum.values())
                .filter(s -> s.getStatus().equals(status))
                .findFirst()
                .orElseThrow(() -> new ValidationException(CodigoErroEnum.ERRO_STATUS_NAO_ENCONTRADO));
    }

}
