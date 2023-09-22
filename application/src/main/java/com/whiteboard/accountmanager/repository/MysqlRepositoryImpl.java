package com.whiteboard.accountmanager.repository;

import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.FiltrosRequestDTO;
import com.whiteboard.accountmanager.exceptions.CadastroException;

import java.io.IOException;
import java.util.List;

public class MysqlRepositoryImpl implements AccountManagerRepository {

    @Override
    public AccountDTO createAccount(AccountDTO dadosConta) throws CadastroException {
        return null;
    }

    @Override
    public AccountDTO getAccount(String usuarioId) throws IOException {
        return null;
    }

    @Override
    public List<AccountDTO> findAccountByFilter(FiltrosRequestDTO filtroRequestDTO) throws IOException, CadastroException {
        return null;
    }
}
