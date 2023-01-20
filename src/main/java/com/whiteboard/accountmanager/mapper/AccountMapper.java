package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.AccountDTO;

import java.util.UUID;

public class AccountMapper {
    public static NewAccountResponsePresentation toAccountMapper(AccountDTO dadosConta) {
        var dataAcconutResponse = new NewAccountResponsePresentation();
        dataAcconutResponse.id(refatorarID(dadosConta.getId()));
        dataAcconutResponse.nome(dadosConta.getNome());
        dataAcconutResponse.cpf(dadosConta.getCpf());
        dataAcconutResponse.email(dadosConta.getEmail());
        dataAcconutResponse.endereco(dadosConta.getEndereco());
        dataAcconutResponse.dataNascimento(dadosConta.getDataNascimento().toString());
        dataAcconutResponse.setStatus(dadosConta.getStatus());
        dataAcconutResponse.dataInclusao(dadosConta.getDataInclusao());
        return dataAcconutResponse;
    }

    private static String refatorarID(UUID uuid) {
        var id = uuid.toString();
        return id.replaceAll("[a-zA-Z\\-]", "");
    }
}
