package com.whiteboard.accountmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountDTO {
    private String id;
    private String documento_id;
    private String nome;
    private String cpf;
    private String email;
    private EnderecoDTO endereco;
    private String dataNascimento;
    private String status;
    private String dataInclusao;
}
