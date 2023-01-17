package com.whiteboard.accountmanager;

import com.codegen.rest.model.EnderecoPresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AccountDTO {
    private UUID id;
    private String nome;
    private String cpf;
    private String email;
    private EnderecoPresentation endereco;
    private LocalDate dataNascimento;
    private String status;
    private String dataInclusao;
}
