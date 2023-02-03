package com.whiteboard.accountmanager.utils;

import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;

@Slf4j
@UtilityClass
public class ValidationUtils {


    public void validarCampos(HashMap<String, String> dadosEntradaMapeados) throws CadastroException {
        log.info("Inicio validação de campos de entrada");
        var camposMapeados = CamposBuscaEnum.listAllCampos();

        for (var campo : camposMapeados) {
            if (campo.getCampo().equals(AccountMapper.getKeyOfMap(dadosEntradaMapeados, campo.getCampo()))) {
                validarValorExiste(campo.getCampo(), dadosEntradaMapeados.get(campo.getCampo()));
                CamposBuscaEnum.buscarEnumByCampo(campo.getCampo()).getRegra().validar(campo.getCampo(), dadosEntradaMapeados.get(campo.getCampo()));
            }
        }
        log.info("Fim validação de campos de entrada");
    }

    private void validarValorExiste(String campo, String validar) throws CadastroException {
        if (ObjectUtils.isEmpty(validar)) {
            throw new CadastroException(CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO.getDescricaoCodigo()
                            .replace("@campo", campo));
        }
    }
}
