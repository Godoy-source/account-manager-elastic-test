package com.whiteboard.accountmanager.pattern;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CamposBuscaRegraStrategyTest {

    private final CamposBuscaEnum campoNome = CamposBuscaEnum.CAMPO_NOME;
    private final CamposBuscaEnum campoCpf = CamposBuscaEnum.CAMPO_CPF;
    private final CamposBuscaEnum campoEmail = CamposBuscaEnum.CAMPO_EMAIL;
    private final CamposBuscaEnum campoRua = CamposBuscaEnum.CAMPO_RUA;
    private final CamposBuscaEnum campoCidade = CamposBuscaEnum.CAMPO_CIDADE;
    private final CamposBuscaEnum campoCep = CamposBuscaEnum.CAMPO_CEP;
    private final CamposBuscaEnum campoEstado = CamposBuscaEnum.CAMPO_ESTADO;
    private final CamposBuscaEnum campoDataNascimento = CamposBuscaEnum.CAMPO_DATA_NASCIMENTO;

    @Test
    void validarRegraCampoNome_sucesso() {
        var mockNome = FiltroDTO.builder()
                .correlationEnumBusca(campoNome)
                .valor("Gabriel")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoNome.getCampo()).getRegra().validar(mockNome));
    }

    @Test
    void validarRegraCampoNome_erroCaracterBloqueado() {
        var mockNome = FiltroDTO.builder()
                .correlationEnumBusca(campoNome)
                .valor("@Gabriel!<>")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoNome.getCampo()).getRegra().validar(mockNome));
    }

    @Test
    void validarRegraCampoNome_erroTamanho() {
        var mockNome = FiltroDTO.builder()
                .correlationEnumBusca(campoNome)
                .valor("Ga")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoNome.getCampo()).getRegra().validar(mockNome));
    }

    @Test
    void validarRegraCampoCpf_sucesso() {
        var mockCpf = FiltroDTO.builder()
                .correlationEnumBusca(campoCpf)
                .valor("00000000000")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoCpf.getCampo()).getRegra().validar(mockCpf));
    }

    @Test
    void validarRegraCampoCpf_erroTamanho() {
        var mockCpf = FiltroDTO.builder()
                .correlationEnumBusca(campoCpf)
                .valor("000000000000")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCpf.getCampo()).getRegra().validar(mockCpf));
    }

    @Test
    void validarRegraCampoCpf_erroSomenteNumeros() {
        var mockCpf = FiltroDTO.builder()
                .correlationEnumBusca(campoCpf)
                .valor("0000000A000")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCpf.getCampo()).getRegra().validar(mockCpf));
    }

    @Test
    void validarRegraCampoEmail_sucesso() {
        var mockEmail = FiltroDTO.builder()
                .correlationEnumBusca(campoEmail)
                .valor("gabrielinaciodev@gmail.com")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail.getCampo()).getRegra().validar(mockEmail));
    }

    @Test
    void validarRegraCampoEmail_erroCaracterBloqueado() {
        var mockEmail = FiltroDTO.builder()
                .correlationEnumBusca(campoEmail)
                .valor("gabr<>elinaciodev@gmail.com")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail.getCampo()).getRegra().validar(mockEmail));
    }

    @Test
    void validarRegraCampoEmail_erroTamanho() {
        var mockEmail = FiltroDTO.builder()
                .correlationEnumBusca(campoEmail)
                .valor("ga")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail.getCampo()).getRegra().validar(mockEmail));
    }

    @Test
    void validarRegraCampoEmail_erroCaracterNecessario() {
        var mockEmail = FiltroDTO.builder()
                .correlationEnumBusca(campoEmail)
                .valor("gabrielinaciodevgmail.com")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEmail.getCampo()).getRegra().validar(mockEmail));
    }

    @Test
    void validarRegraCampoRua_sucesso() {
        var mockRua = FiltroDTO.builder()
                .correlationEnumBusca(campoRua)
                .valor("Rua do Zé 123")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoRua.getCampo()).getRegra().validar(mockRua));
    }

    @Test
    void validarRegraCampoRua_erroTamanho() {
        var mockRua = FiltroDTO.builder()
                .correlationEnumBusca(campoRua)
                .valor("Rua")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoRua.getCampo()).getRegra().validar(mockRua));
    }

    @Test
    void validarRegraCampoRua_erroCaracterBloqueado() {
        var mockRua = FiltroDTO.builder()
                .correlationEnumBusca(campoRua)
                .valor("Rua <>do Zé 123")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoRua.getCampo()).getRegra().validar(mockRua));
    }

    @Test
    void validarRegraCampoCidade_sucesso() {
        var mockCidade = FiltroDTO.builder()
                .correlationEnumBusca(campoRua)
                .valor("Uberlândia")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoCidade.getCampo()).getRegra().validar(mockCidade));
    }

    @Test
    void validarRegraCampoCidade_erroTamanho() {
        var mockCidade = FiltroDTO.builder()
                .correlationEnumBusca(campoRua)
                .valor("hum")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCidade.getCampo()).getRegra().validar(mockCidade));
    }

    @Test
    void validarRegraCampoCidade_erroCaracterBloqueado() {
        var mockCidade = FiltroDTO.builder()
                .correlationEnumBusca(campoRua)
                .valor("Uberlândi<>a")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCidade.getCampo()).getRegra().validar(mockCidade));
    }

    @Test
    void validarRegraCampoEstado_sucesso() {
        var mockEstado = FiltroDTO.builder()
                .correlationEnumBusca(campoEstado)
                .valor("MG")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoEstado.getCampo()).getRegra().validar(mockEstado));
    }

    @Test
    void validarRegraCampoEstado_erroTamanho() {
        var mockEstado = FiltroDTO.builder()
                .correlationEnumBusca(campoEstado)
                .valor("M")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEstado.getCampo()).getRegra().validar(mockEstado));
    }

    @Test
    void validarRegraCampoEstado_erroCaracterBloqueado() {
        var mockEstado = FiltroDTO.builder()
                .correlationEnumBusca(campoEstado)
                .valor("MG<")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoEstado.getCampo()).getRegra().validar(mockEstado));
    }

    @Test
    void validarRegraCampoCep_sucesso() {
        var mockCep = FiltroDTO.builder()
                .correlationEnumBusca(campoCep)
                .valor("00000000")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoCep.getCampo()).getRegra().validar(mockCep));
    }

    @Test
    void validarRegraCampoCep_erroTamanho() {
        var mockCep = FiltroDTO.builder()
                .correlationEnumBusca(campoCep)
                .valor("000000000")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCep.getCampo()).getRegra().validar(mockCep));
    }

    @Test
    void validarRegraCampoCep_erroApenasNumeros() {
        var mockCep = FiltroDTO.builder()
                .correlationEnumBusca(campoCep)
                .valor("0000<A00")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoCep.getCampo()).getRegra().validar(mockCep));
    }

    @Test
    void validarRegraCampoDataNascimento_sucesso() {
        var mockDataNascimento = FiltroDTO.builder()
                .correlationEnumBusca(campoCep)
                .valor("24-01-2004")
                .build();
        Assertions.assertDoesNotThrow(() ->
                CamposBuscaEnum.buscarEnumByCampo(campoDataNascimento.getCampo()).getRegra().validar(mockDataNascimento ));
    }

    @Test
    void validarRegraCampoDataNascimento_erroTamanho() {
        var mockDataNascimento = FiltroDTO.builder()
                .correlationEnumBusca(campoCep)
                .valor("24012004")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoDataNascimento.getCampo()).getRegra().validar(mockDataNascimento));
    }

    @Test
    void validarRegraCampoDataNascimento_erroCaracterBloqueado() {
        var mockDataNascimento = FiltroDTO.builder()
                .correlationEnumBusca(campoCep)
                .valor("24'01'2004")
                .build();
        Assertions.assertThrows(ValidationException.class, () ->
                CamposBuscaEnum.buscarEnumByCampo(campoDataNascimento.getCampo()).getRegra().validar(mockDataNascimento));
    }

}
