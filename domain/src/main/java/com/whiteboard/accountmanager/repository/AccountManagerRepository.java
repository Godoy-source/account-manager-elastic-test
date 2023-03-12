package com.whiteboard.accountmanager.repository;

import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.exceptions.CadastroException;

import java.io.IOException;
import java.util.List;

public interface AccountManagerRepository {
    AccountDTO createAccount(AccountDTO dadosConta) throws CadastroException;

    AccountDTO getAccount(String usuarioId) throws IOException;

    List<AccountDTO> findAccountByFilter(FiltrosRequestDTO filtroRequestDTO) throws IOException, CadastroException;
}
