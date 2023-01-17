package com.whiteboard.accountmanager.service;


import com.codegen.rest.model.NewAccountRequestPresentation;
import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.AccountDTO;
import com.whiteboard.accountmanager.exceptions.CadastroNaoEfetivadoException;
import com.whiteboard.accountmanager.repository.AccountManagerRepositoryImpl;
import com.whiteboard.accountmanager.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountManagerRepositoryImpl repository;
    public NewAccountResponsePresentation saveDataAccount(NewAccountRequestPresentation dadosConta) throws CadastroNaoEfetivadoException {
        var dadosContaDTO = toSaveAccount(dadosConta);
        return repository.createAccount(dadosContaDTO);
    }

    public static AccountDTO toSaveAccount(NewAccountRequestPresentation dadosConta) {
        return AccountDTO.builder()
                .id(UUID.randomUUID())
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
