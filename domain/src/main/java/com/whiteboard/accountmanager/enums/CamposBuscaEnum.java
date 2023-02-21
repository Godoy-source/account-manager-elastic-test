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
    CAMPO_CEP("endereco.cep", new CampoCepRegraStrategy()),
    CAMPO_RUA("endereco.rua", new CampoRuaRegraStrategy()),
    CAMPO_CIDADE("endereco.cidade", new CampoCidadeRegraStrategy()),
    CAMPO_ESTADO("endereco.estado", new CampoEstadoRegraStrategy()),
    CAMPO_DATA_NASCIMENTO("dataNascimento", new CampoDataNascimentoRegraStrategy()),
    CAMPO_STATUS("status", new CampoStatusRegraStrategy()),
    CAMPO_DT_INCLUSAO("dataInclusao", new CampoDtInclusaoRegraStrategy());

    private final String campo;
    private final CamposBuscaStrategy regra;

    public static CamposBuscaEnum buscarEnumByCampo(String campo) {
        return Arrays.stream(CamposBuscaEnum.values())
                .filter(e -> e.getCampo().contains(campo))
                .findFirst()
                .orElseThrow();
    }

    public static List<CamposBuscaEnum> listAllCampos() {
        return List.of(CamposBuscaEnum.values());
    }

    public static List<CamposBuscaEnum> listSearchCamposPrimarios() {
        return List.of(CamposBuscaEnum.CAMPO_CPF, CamposBuscaEnum.CAMPO_EMAIL);
    }
}
