package com.whiteboard.accountmanager.dto;

import com.whiteboard.accountmanager.enums.TipoBuscaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FiltrosRequestDTO {
    private List<FiltroDTO> filtros;
    private TipoBuscaEnum tipoBusca;
}