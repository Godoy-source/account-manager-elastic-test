package com.whiteboard.accountmanager.dto;

import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FiltroDTO {
    private CamposBuscaEnum correlationEnumBusca;
    private String de;
    private String para;
    private String valor;
}
