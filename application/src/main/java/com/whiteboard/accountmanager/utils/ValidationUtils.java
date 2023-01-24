package com.whiteboard.accountmanager.utils;

import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@UtilityClass
public class ValidationUtils {

    private static final List<String> charactersBloqueados = List.of("<", ">", "/", "[", "]", "'", "^", "!");

    public void validarCampos(NewAccountRequestPresentation dadosConta) throws CadastroException {
        validar("nome", dadosConta.getNome(), 4, 255, false);
        validar("cpf", dadosConta.getCpf(), 11, 11, true);
        validar("email", dadosConta.getEmail(), 5, 100, false);
        validar("cep", dadosConta.getEndereco().getCep(), 8 ,8, true);
        validar("rua", dadosConta.getEndereco().getCep(), 5 ,100, false);
        validar("cidade", dadosConta.getEndereco().getCep(), 5 ,50, false);
        validar("estado", dadosConta.getEndereco().getCep(), 5 ,50, false);
        validar("dataNascimento", dadosConta.getDataNascimento().toString(), 10,10, false);
    }

    private void validar(String campo, String validar, int minTamanho, int maxTamanho, boolean somenteNumeros) throws CadastroException {
        validarValorExiste(campo, validar);
        validarTamanho(campo, validar, minTamanho, maxTamanho);
        if(somenteNumeros) {
            validarSomenteNumeros(campo, validar);
        } else {
            validarCharacters(campo, validar);
        }
    }

    private void validarValorExiste(String campo, String validar) throws CadastroException {
        if(ObjectUtils.isEmpty(validar)) {
            throw new CadastroException(CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO.getDescricaoCodigo()
                            .replace("@campo", campo));
        }
    }

    private void validarSomenteNumeros(String campo, String validar) throws CadastroException {
        if(!StringUtils.isNumeric(validar)) {
            throw new CadastroException(CodigoErroEnum.ERRO_DADOS_ENTRADA_VALOR_INVALIDO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_VALOR_INVALIDO.getDescricaoCodigo()
                            .replace("@campo", campo));
        }
    }

    private void validarTamanho(String campo, String validar, int minTamanho, int maxTamanho) throws CadastroException {
        if(validar.length() > maxTamanho || validar.length() < minTamanho) {
            throw new CadastroException(CodigoErroEnum.ERRO_DADOS_ENTRADA_TAMANHO_CAMPO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_TAMANHO_CAMPO.getDescricaoCodigo()
                            .replace("@campo", campo)
                            .replace("@limiteMin", minTamanho + "")
                            .replace("@limiteMax", maxTamanho + ""));
        }
    }

    private void validarCharacters(String campo, String validar) throws CadastroException {
        for (int i = 0; i < validar.length(); i++) {
            var strChar = Character.toString(validar.charAt(i));
            if (charactersBloqueados.contains(strChar)) {
                throw new CadastroException(CodigoErroEnum.ERRO_DADOS_ENTRADA_NAO_PERMITIDOS,
                        CodigoErroEnum.ERRO_DADOS_ENTRADA_NAO_PERMITIDOS.getDescricaoCodigo()
                                .replace("@character", strChar)
                                .replace("@campo", campo));
            }
        }
    }
}
