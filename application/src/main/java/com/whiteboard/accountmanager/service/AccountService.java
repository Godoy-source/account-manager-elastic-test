package com.whiteboard.accountmanager.service;


import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.enums.CodigoErroEnum;
import com.whiteboard.accountmanager.exceptions.CadastroException;
import com.whiteboard.accountmanager.repository.AccountManagerRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AccountService {
    private AccountManagerRepositoryImpl repository;

    public AccountDTO saveDataAccount(AccountDTO dadosConta, FiltrosRequestDTO filtros) throws CadastroException {
        var contaExistente = repository.findAccountByFilter(filtros);
        if (ObjectUtils.isNotEmpty(contaExistente)) {
            log.error("Os dados para {} ou {} ja se encontram nos nossos registros.", dadosConta.getCpf(), dadosConta.getEmail());
            throw new CadastroException(CodigoErroEnum.ERRO_CONTA_ANTERIORMENTE_REGISTRADA);
        }
        return repository.createAccount(dadosConta);
    }

    public AccountDTO getUserAccountById(String usuarioId) throws CadastroException, IOException {
        if (!repository.verifyDocExists(usuarioId)) {
            log.error("Não foi possivel encontrar os dados da conta: {}", usuarioId);
            throw new CadastroException(CodigoErroEnum.ERRO_CONTA_NAO_ENCONTRADA);
        }
        return repository.getAccount(usuarioId);
    }

    public List<AccountDTO> searchAccounts(FiltrosRequestDTO filtroRequestDTO) throws CadastroException {
        return repository.findAccountByFilter(filtroRequestDTO);
    }
}
