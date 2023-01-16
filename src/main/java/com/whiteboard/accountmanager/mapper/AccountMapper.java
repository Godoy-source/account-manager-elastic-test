package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.NewAccountRequestPresentation;
import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.utils.DataUtils;

import java.time.OffsetDateTime;

public class AccountMapper {
    public static NewAccountResponsePresentation toAccountMapper(NewAccountRequestPresentation dadosConta) {
        var dataAcconutResponse = new NewAccountResponsePresentation();
        dataAcconutResponse.id(0l);
        dataAcconutResponse.nome(dadosConta.getNome());
        dataAcconutResponse.cpf(dadosConta.getCpf());
        dataAcconutResponse.email(dadosConta.getEmail());
        dataAcconutResponse.endereco(dadosConta.getEndereco());
        dataAcconutResponse.dataNascimento(dadosConta.getDataNascimento());
        dataAcconutResponse.setStatus("A");
        dataAcconutResponse.dataInclusao(DataUtils.formatOffsetData(OffsetDateTime.now()));
        return dataAcconutResponse;
    }
}
