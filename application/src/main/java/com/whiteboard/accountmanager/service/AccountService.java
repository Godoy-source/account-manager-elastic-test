package com.whiteboard.accountmanager.service;


import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.EnderecoDTO;
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

    public AccountDTO saveDataAccount(NewAccountRequestPresentation dadosConta) throws CadastroNaoEfetivadoException {
        var dadosContaDTO = convertPresentationToDTO(dadosConta);
        return repository.createAccount(dadosContaDTO);
    }

    private static AccountDTO convertPresentationToDTO(NewAccountRequestPresentation dadosConta) {
        var id = UUID.randomUUID();
        return AccountDTO.builder()
                .id(refatorarID(id))
                .documento_id(id)
                .nome(dadosConta.getNome())
                .cpf(dadosConta.getCpf())
                .email(dadosConta.getEmail())
                .endereco(EnderecoDTO.builder()
                        .cidade(dadosConta.getEndereco().getCidade())
                        .rua(dadosConta.getEndereco().getRua())
                        .cep(dadosConta.getEndereco().getCep())
                        .estado(dadosConta.getEndereco().getEstado())
                        .build())
                .dataNascimento(dadosConta.getDataNascimento().toString())
                .status("A")
                .dataInclusao(DataUtils.formatOffsetData(OffsetDateTime.now()))
                .build();
    }

    private static String refatorarID(UUID uuid) {
        var id = uuid.toString();
        return id.replaceAll("[a-zA-Z\\-]", "");
    }
}
