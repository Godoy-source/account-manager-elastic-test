package com.whiteboard.accountmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EnderecoDTO {
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
}
