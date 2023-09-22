package com.whiteboard.accountmanager.dto;

import com.whiteboard.accountmanager.enums.TipoBuscaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltrosRequestDTO {
    private List<FiltroDTO> filtros;
    private TipoBuscaEnum tipoBusca;
    private Integer pagina = 0;
    private Integer tamanhoPagina;
}
