package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.AccountDTO;

public class AccountMapper {
    public static NewAccountResponsePresentation toAccountMapper(AccountDTO dadosConta) {
        var dataAcconutResponse = new NewAccountResponsePresentation();
        dataAcconutResponse.id(dadosConta.getId().toString());
        dataAcconutResponse.nome(dadosConta.getNome());
        dataAcconutResponse.cpf(dadosConta.getCpf());
        dataAcconutResponse.email(dadosConta.getEmail());
        dataAcconutResponse.endereco(dadosConta.getEndereco());
        dataAcconutResponse.dataNascimento(dadosConta.getDataNascimento());
        dataAcconutResponse.setStatus(dadosConta.getStatus());
        dataAcconutResponse.dataInclusao(dadosConta.getDataInclusao());
        return dataAcconutResponse;
    }
}
