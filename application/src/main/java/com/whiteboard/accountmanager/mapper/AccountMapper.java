package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.EnderecoPresentation;
import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.EnderecoDTO;

public class AccountMapper {
    public static NewAccountResponsePresentation toAccountMapper(AccountDTO dadosConta) {
        var dataAcconutResponse = new NewAccountResponsePresentation();
        dataAcconutResponse.id(dadosConta.getId());
        dataAcconutResponse.nome(dadosConta.getNome());
        dataAcconutResponse.cpf(dadosConta.getCpf());
        dataAcconutResponse.email(dadosConta.getEmail());
        dataAcconutResponse.endereco(toConvertEndereco(dadosConta.getEndereco()));
        dataAcconutResponse.dataNascimento(dadosConta.getDataNascimento());
        dataAcconutResponse.setStatus(dadosConta.getStatus());
        dataAcconutResponse.dataInclusao(dadosConta.getDataInclusao());
        return dataAcconutResponse;
    }

    private static EnderecoPresentation toConvertEndereco(EnderecoDTO endereco) {
        var enderecoConverted = new EnderecoPresentation();
        enderecoConverted.setCep(endereco.getCep());
        enderecoConverted.setCidade(endereco.getCidade());
        enderecoConverted.setEstado(endereco.getEstado());
        enderecoConverted.setRua(endereco.getRua());
        return enderecoConverted;
    }
}
