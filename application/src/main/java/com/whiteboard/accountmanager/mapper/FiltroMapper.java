package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.FiltrosAccountRequestPresentation;
import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroMapper {
    public static List<FiltroDTO> toFiltroMapper(FiltrosAccountRequestPresentation filtrosAccountRequestPresentation)  {
        return filtrosAccountRequestPresentation.getFiltros()
                .stream()
                .map(filtro -> FiltroDTO.builder()
                        .correlationEnumBusca(CamposBuscaEnum.buscarEnumByCampo(filtro.getCampo().getValue()))
                        .de(filtro.getDe())
                        .para(filtro.getPara())
                        .valor(filtro.getValor())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<FiltroDTO> montarFiltrosDadosEntrada(HashMap<String, String> dadosEntradaMapeados) {
        var pesquisarPor = CamposBuscaEnum.listSearchCamposPrimarios();
        return pesquisarPor.stream()
                .map(filtro -> FiltroDTO.builder()
                        .correlationEnumBusca(filtro)
                        .valor(dadosEntradaMapeados.get(filtro.getCampo()))
                        .build())
                .collect(Collectors.toList());
    }
}
