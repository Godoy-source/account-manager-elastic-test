package com.whiteboard.accountmanager.enums;

import com.whiteboard.accountmanager.pattern.CamposBuscaStrategy;
import com.whiteboard.accountmanager.pattern.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum CamposBuscaEnum {
    CAMPO_NOME("nome", new CampoNomeRegraStrategy()),
    CAMPO_CPF("cpf", new CampoCpfRegraStrategy()),
    CAMPO_EMAIL("email", new CampoEmailRegraStrategy()),
    CAMPO_CEP("cep", new CampoCepRegraStrategy()),
    CAMPO_RUA("rua", new CampoRuaRegraStrategy()),
    CAMPO_CIDADE("cidade", new CampoCidadeRegraStrategy()),
    CAMPO_ESTADO("estado", new CampoEstadoRegraStrategy()),
    CAMPO_DATA_NASCIMENTO("dataNascimento", new CampoDataNascimentoRegraStrategy());

    private String campo;
    private CamposBuscaStrategy regra;

    public static CamposBuscaEnum buscarEnumByCampo(String campo) {
        return Arrays.stream(CamposBuscaEnum.values())
                .filter(e -> e.getCampo().equals(campo))
                .findFirst()
                .orElseThrow();
    }

    public static List<CamposBuscaEnum> listAllCampos() {
        return List.of(CamposBuscaEnum.values());
    }
}
