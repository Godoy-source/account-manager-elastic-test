package com.whiteboard.accountmanager.mapper;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class PessoaMapper {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String dataInclusao;
}
