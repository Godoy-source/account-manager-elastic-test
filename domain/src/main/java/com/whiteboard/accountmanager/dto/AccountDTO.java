package com.whiteboard.accountmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AccountDTO {
    private String id;
    private UUID documento_id;
    private String nome;
    private String cpf;
    private String email;
    private EnderecoDTO endereco;
    private String dataNascimento;
    private String status;
    private String dataInclusao;
}
