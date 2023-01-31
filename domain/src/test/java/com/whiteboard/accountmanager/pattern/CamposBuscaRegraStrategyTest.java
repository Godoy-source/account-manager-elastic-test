package com.whiteboard.accountmanager.pattern;

import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CamposBuscaRegraStrategyTest {

    private final String campoNome = CamposBuscaEnum.CAMPO_NOME.getCampo();
    private final String campoCpf  = CamposBuscaEnum.CAMPO_CPF.getCampo();
    private final String campoEmail = CamposBuscaEnum.CAMPO_EMAIL.getCampo();
    private final String campoRua = CamposBuscaEnum.CAMPO_RUA.getCampo();
    private final String campoCidade = CamposBuscaEnum.CAMPO_CIDADE.getCampo();
    private final String campoCep = CamposBuscaEnum.CAMPO_CEP.getCampo();
    private final String campoEstado = CamposBuscaEnum.CAMPO_ESTADO.getCampo();
    private final String campoDataNascimento = CamposBuscaEnum.CAMPO_DATA_NASCIMENTO.getCampo();

    @Test
    void validarRegraCampoNome_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoNome).getRegra().validar(campoNome, "Gabriel"));
    }

    @Test
    void validarRegraCampoNome_erroCaracterBloqueado() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoNome).getRegra().validar(campoNome, "@Gabriel!<>"));
    }

    @Test
    void validarRegraCampoNome_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoNome).getRegra().validar(campoNome, "Ga"));
    }

    @Test
    void validarRegraCampoCpf_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoCpf).getRegra().validar(campoCpf, "00000000000"));
    }

    @Test
    void validarRegraCampoCpf_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCpf).getRegra().validar(campoCpf, "000000000000"));
    }

    @Test
    void validarRegraCampoCpf_erroSomenteNumeros() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCpf).getRegra().validar(campoCpf, "0000000A000"));
    }

    @Test
    void validarRegraCampoEmail_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail).getRegra().validar(campoEmail, "gabrielinaciodev@gmail.com"));
    }

    @Test
    void validarRegraCampoEmail_erroCaracterBloqueado() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail).getRegra().validar(campoEmail, "@Gabriel!<>"));
    }

    @Test
    void validarRegraCampoEmail_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail).getRegra().validar(campoEmail, "ga"));
    }

    @Test
    void validarRegraCampoEmail_erroCaracterNecessario() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail).getRegra().validar(campoEmail, "gabrielinaciodevgmail.com"));
    }

    @Test
    void validarRegraCampoRua_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoRua).getRegra().validar(campoRua, "Rua do Zé 123"));
    }

    @Test
    void validarRegraCampoRua_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoRua).getRegra().validar(campoRua, "Rua"));
    }

    @Test
    void validarRegraCampoRua_erroCaracterBloqueado() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoRua).getRegra().validar(campoRua, "Rua <>do Zé 123"));
    }

    @Test
    void validarRegraCampoCidade_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoCidade).getRegra().validar(campoCidade, "Uberlândia"));
    }

    @Test
    void validarRegraCampoCidade_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCidade).getRegra().validar(campoCidade, "hum"));
    }

    @Test
    void validarRegraCampoCidade_erroCaracterBloqueado() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCidade).getRegra().validar(campoCidade, "Uberlândi<>a"));
    }

    @Test
    void validarRegraCampoEstado_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoEstado).getRegra().validar(campoCep, "MG"));
    }

    @Test
    void validarRegraCampoEstado_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEstado).getRegra().validar(campoCep, "M"));
    }

    @Test
    void validarRegraCampoEstado_erroCaracterBloqueado() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEstado).getRegra().validar(campoCep, "MG<"));
    }

    @Test
    void validarRegraCampoCep_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoCep).getRegra().validar(campoCep, "00000000"));
    }

    @Test
    void validarRegraCampoCep_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCep).getRegra().validar(campoCep, "000000000"));
    }

    @Test
    void validarRegraCampoCep_erroApenasNumeros() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCep).getRegra().validar(campoCep, "0000<A00"));
    }

    @Test
    void validarRegraCampoDataNascimento_sucesso() {
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoDataNascimento).getRegra().validar(campoDataNascimento, "24-01-2004"));
    }

    @Test
    void validarRegraCampoDataNascimento_erroTamanho() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoDataNascimento).getRegra().validar(campoDataNascimento, "24012004"));
    }

    @Test
    void validarRegraCampoDataNascimento_erroCaracterBloqueado() {
        Assertions.assertThrows(CadastroException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoDataNascimento).getRegra().validar(campoDataNascimento, "24'01'2004"));
    }

}
