package com.whiteboard.accountmanager.utils;

import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.exceptions.ValidationException;
import com.whiteboard.accountmanager.mapper.AccountMapper;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.List;

@UtilityClass
public class ValidationUtils {

    public void validarCampos(HashMap<String, String> dadosEntradaMapeados) throws ValidationException {
        var camposMapeados = CamposBuscaEnum.listAllCampos();
        for (var campo : camposMapeados) {
            if (campo.getCampo().equals(AccountMapper.getKeyOfMap(dadosEntradaMapeados, campo.getCampo()))) {
                validarValorExiste(campo.getCampo(), dadosEntradaMapeados.get(campo.getCampo()));
                CamposBuscaEnum.buscarEnumByCampo(campo.getCampo()).getRegra().validar(campo.getCampo(), dadosEntradaMapeados.get(campo.getCampo()));
            }
        }
    }

    public void validarFiltros(List<FiltroDTO> filtros) throws ValidationException {
        for (var filtro : filtros) {
            CamposBuscaEnum.buscarEnumByCampo(filtro.getCorrelationEnumBusca().getCampo()).getRegra().validar(filtro.getCorrelationEnumBusca().getCampo(), filtro.getValor());
        }
    }

    private void validarValorExiste(String campo, String validar) throws ValidationException {
        if (ObjectUtils.isEmpty(validar)) {
            throw new ValidationException(CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO,
                    CodigoErroEnum.ERRO_DADOS_ENTRADA_NULO.getDescricaoCodigo()
                            .replace("@campo", campo));
        }
    }
}
