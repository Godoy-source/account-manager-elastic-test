package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.AccountResponsePresentation;
import com.codegen.rest.model.EnderecoPresentation;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.EnderecoDTO;
import com.whiteboard.accountmanager.enums.CamposBuscaEnum;
import com.whiteboard.accountmanager.utils.DataUtils;

import java.time.OffsetDateTime;
import java.util.*;

public class AccountMapper {
    public static AccountResponsePresentation toAccountMapper(AccountDTO dadosConta) {
        var dataAcconutResponse = new AccountResponsePresentation();
        dataAcconutResponse.id(dadosConta.getId());
        dataAcconutResponse.nome(dadosConta.getNome());
        dataAcconutResponse.cpf(dadosConta.getCpf());
        dataAcconutResponse.email(dadosConta.getEmail());
        dataAcconutResponse.endereco(toConvertEndereco(dadosConta.getEndereco()));
        dataAcconutResponse.dataNascimento(dadosConta.getDataNascimento());
        dataAcconutResponse.setStatus(dadosConta.getStatus());
        dataAcconutResponse.dataInclusao(dadosConta.getDataInclusao());
        return dataAcconutResponse;
    }

    public static List<AccountResponsePresentation> toAccountListMapper(List<AccountDTO> dadosContas) {
        List<AccountResponsePresentation> listDataAcconutResponse = new ArrayList<>();
        for (var conta : dadosContas) {
            var dataAcconutResponse = new AccountResponsePresentation();
            dataAcconutResponse.id(conta.getId());
            dataAcconutResponse.nome(conta.getNome());
            dataAcconutResponse.cpf(conta.getCpf());
            dataAcconutResponse.email(conta.getEmail());
            dataAcconutResponse.endereco(toConvertEndereco(conta.getEndereco()));
            dataAcconutResponse.dataNascimento(conta.getDataNascimento());
            dataAcconutResponse.setStatus(conta.getStatus());
            dataAcconutResponse.dataInclusao(conta.getDataInclusao());
            listDataAcconutResponse.add(dataAcconutResponse);
        }
        return listDataAcconutResponse;
    }

    public static AccountDTO convertPresentationToDTO(NewAccountRequestPresentation dadosConta) {
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

    public static HashMap<String, String> mapearDadosEntrada(NewAccountRequestPresentation dadosEntrada) {
        var map = new HashMap<String, String>();
        map.put(CamposBuscaEnum.CAMPO_NOME.getCampo(), dadosEntrada.getNome());
        map.put(CamposBuscaEnum.CAMPO_CPF.getCampo(), dadosEntrada.getCpf());
        map.put(CamposBuscaEnum.CAMPO_EMAIL.getCampo(), dadosEntrada.getEmail());
        map.put(CamposBuscaEnum.CAMPO_CEP.getCampo(), dadosEntrada.getEndereco().getCep());
        map.put(CamposBuscaEnum.CAMPO_RUA.getCampo(), dadosEntrada.getEndereco().getRua());
        map.put(CamposBuscaEnum.CAMPO_CIDADE.getCampo(), dadosEntrada.getEndereco().getCidade());
        map.put(CamposBuscaEnum.CAMPO_ESTADO.getCampo(), dadosEntrada.getEndereco().getEstado());
        map.put(CamposBuscaEnum.CAMPO_DATA_NASCIMENTO.getCampo(), dadosEntrada.getDataNascimento().toString());
        return map;
    }

    public static String getKeyOfMap(Map<String, String> map, String value) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(value))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    private static EnderecoPresentation toConvertEndereco(EnderecoDTO endereco) {
        var enderecoConverted = new EnderecoPresentation();
        enderecoConverted.setCep(endereco.getCep());
        enderecoConverted.setCidade(endereco.getCidade());
        enderecoConverted.setEstado(endereco.getEstado());
        enderecoConverted.setRua(endereco.getRua());
        return enderecoConverted;
    }

    private static String refatorarID(UUID uuid) {
        var id = uuid.toString();
        return id.replaceAll("[a-zA-Z\\-]", "");
    }
}
