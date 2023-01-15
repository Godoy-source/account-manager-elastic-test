package com.whiteboard.accountmanager;

import com.codegen.rest.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Endereco endereco;
    private LocalDate dataNascimento;
    private String status;
    private String dataInclusao;
}
