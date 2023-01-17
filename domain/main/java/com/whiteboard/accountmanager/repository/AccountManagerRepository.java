package com.whiteboard.accountmanager.repository;

import com.codegen.rest.model.NewAccountResponsePresentation;
import com.whiteboard.accountmanager.AccountDTO;
import com.whiteboard.accountmanager.exceptions.CadastroNaoEfetivadoException;

public interface AccountManagerRepository {
    NewAccountResponsePresentation createAccount(AccountDTO dadosConta) throws CadastroNaoEfetivadoException;
}
