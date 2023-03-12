package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.FiltrosAccountRequestPresentation;
import com.whiteboard.accountmanager.dto.FiltroDTO;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.enums.TipoBuscaEnum;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroMapper {
    public static FiltrosRequestDTO toFiltroRequestMapper(Integer pagina, Integer tamanhoPagina, FiltrosAccountRequestPresentation filtrosAccountRequestPresentation) {
        return FiltrosRequestDTO.builder()
                .tipoBusca(TipoBuscaEnum.buscarEnumByTipo(filtrosAccountRequestPresentation.getModo().getValue()))
                .filtros(filtrosAccountRequestPresentation.getFiltros()
                        .stream()
                        .map(filtro -> FiltroDTO.builder()
                                .correlationEnumBusca(CamposBuscaEnum.buscarEnumByCampo(filtro.getCampo().getValue()))
                                .de(filtro.getDe())
                                .para(filtro.getPara())
                                .valor(filtro.getValor())
                                .build())
                        .collect(Collectors.toList()))
                .pagina(pagina)
                .tamanhoPagina(tamanhoPagina)
                .build();
    }

    public static FiltrosRequestDTO chavesPrincipais(List<FiltroDTO> filtros) {
        return FiltrosRequestDTO.builder()
                .tipoBusca(TipoBuscaEnum.AGREGAR)
                .filtros(filtros.stream()
                        .filter(filtro -> CamposBuscaEnum.listSearchCamposPrimarios().contains(filtro.getCorrelationEnumBusca()))
                        .collect(Collectors.toList()))
                .build();
    }
}
