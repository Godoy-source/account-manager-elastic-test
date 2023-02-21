package com.whiteboard.accountmanager.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CamposBuscaEnumTest {

    @Test
    void buscarEnumByCampo_sucesso() {
        var busca = CamposBuscaEnum.buscarEnumByCampo("cpf");
        Assertions.assertEquals(busca, CamposBuscaEnum.CAMPO_CPF);
    }

    @Test
    void buscarEnumByCampo_erro() {
        Assertions.assertThrows(Exception.class, () -> CamposBuscaEnum.buscarEnumByCampo("campo_invalido"));
    }

    @Test
    void listarTodosOsCampos_sucesso() {
        var enuns = CamposBuscaEnum.listAllCampos();
        Assertions.assertEquals(10, enuns.size());
    }
}
