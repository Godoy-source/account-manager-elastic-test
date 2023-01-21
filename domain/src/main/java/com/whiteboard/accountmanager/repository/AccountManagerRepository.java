package com.whiteboard.accountmanager.repository;

import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.exceptions.CadastroNaoEfetivadoException;

public interface AccountManagerRepository {
    AccountDTO createAccount(AccountDTO dadosConta) throws CadastroNaoEfetivadoException;
}
