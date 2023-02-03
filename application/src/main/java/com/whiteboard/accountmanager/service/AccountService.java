package com.whiteboard.accountmanager.service;


import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.EnderecoDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.repository.AccountManagerRepositoryImpl;
import com.whiteboard.accountmanager.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountManagerRepositoryImpl repository;

    public AccountDTO saveDataAccount(NewAccountRequestPresentation dadosConta, HashMap<String, String> dadosEntradaMapeados) throws CadastroException, IOException {
        var camposParaPesquisa = CamposBuscaEnum.listSearchCampos();
        var verificarRegistro = repository.findAccountByFilter(dadosEntradaMapeados, camposParaPesquisa);
        if (ObjectUtils.isNotEmpty(verificarRegistro)) {
            throw new CadastroException(CodigoErroEnum.ERRO_CONTA_ANTERIORMENTE_REGISTRADA);
        }
        var dadosContaDTO = convertPresentationToDTO(dadosConta);
        return repository.createAccount(dadosContaDTO);
    }

    public AccountDTO getUserAccount(String usuarioId) throws CadastroException, IOException {
        if (!repository.verifyDocExists(usuarioId)) {
            throw new CadastroException(CodigoErroEnum.ERRO_CONTA_NAO_ENCONTRADA);
        }
        return repository.getAccount(usuarioId);
    }

    public List<AccountDTO> searchAccount(HashMap<String, String> dadosEntradaMapeados) throws IOException {
        var camposParaPesquisa = CamposBuscaEnum.listAllCampos();
        return repository.findAccountByFilter(dadosEntradaMapeados, camposParaPesquisa);
    }

    private static AccountDTO convertPresentationToDTO(NewAccountRequestPresentation dadosConta) {
        var id = UUID.randomUUID();
        return AccountDTO.builder()
                .id(refatorarID(id))
                .documento_id(id.toString())
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
