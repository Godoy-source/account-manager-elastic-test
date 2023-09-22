package com.whiteboard.accountmanager.utils;

import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@UtilityClass
public class ValidationCamposUtils {
    private static final List<String> charactersBloqueados = List.of("<", ">", "/", "[", "]", "'", "^", "!", ":");

    public void validarSomenteNumeros(String campo, String validar) throws ValidationException {
        if (!StringUtils.isNumeric(validar)) {
            throw new ValidationException(CodigoErroEnum.ERRO_DADOS_ENTRADA_VALOR_INVALIDO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_VALOR_INVALIDO.getDescricaoCodigo()
                            .replace("@campo", campo));
        }
    }

    public void validarTamanho(String campo, String validar, int minTamanho, int maxTamanho) throws ValidationException {
        if (validar.length() > maxTamanho || validar.length() < minTamanho) {
            throw new ValidationException(CodigoErroEnum.ERRO_DADOS_ENTRADA_TAMANHO_CAMPO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_TAMANHO_CAMPO.getDescricaoCodigo()
                            .replace("@campo", campo)
                            .replace("@limiteMin", minTamanho + "")
                            .replace("@limiteMax", maxTamanho + ""));
        }
    }

    public void validarCharacters(String campo, String validar) throws ValidationException {
        for (int i = 0; i < validar.length(); i++) {
            var strChar = Character.toString(validar.charAt(i));
            if (charactersBloqueados.contains(strChar)) {
                throw new ValidationException(CodigoErroEnum.ERRO_DADOS_ENTRADA_NAO_PERMITIDOS,
                        CodigoErroEnum.ERRO_DADOS_ENTRADA_NAO_PERMITIDOS.getDescricaoCodigo()
                                .replace("@character", strChar)
                                .replace("@campo", campo));
            }
        }
    }

    public void validarValorExiste(String campo, String validar) throws ValidationException {
        if (ObjectUtils.isEmpty(validar)) {
            throw new ValidationException(CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO.getDescricaoCodigo()
                            .replace("@campo", campo));
        }
    }
}
