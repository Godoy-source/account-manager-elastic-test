package com.whiteboard.accountmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("documento_id")
    private String documento_id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("email")
    private String email;
    @JsonProperty("endereco")
    private EnderecoDTO endereco;
    @JsonProperty("dataNascimento")
    private String dataNascimento;
    @JsonProperty("status")
    private String status;
    @JsonProperty("dataInclusao")
    private String dataInclusao;
}
