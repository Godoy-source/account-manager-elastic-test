package com.whiteboard.accountmanager.service;


import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.AccountDTO;
import com.whiteboard.accountmanager.utils.DataUtils;

import java.time.OffsetDateTime;

public class AccountService {
    public static AccountDTO toSaveAccount(NewAccountRequestPresentation dadosConta) {
        return AccountDTO.builder()
                .nome(dadosConta.getNome())
                .cpf(dadosConta.getCpf())
                .email(dadosConta.getEmail())
                .endereco(dadosConta.getEndereco())
                .dataNascimento(dadosConta.getDataNascimento())
                .status("A")
                .dataInclusao(DataUtils.formatOffsetData(OffsetDateTime.now()))
                .build();
    }
}
