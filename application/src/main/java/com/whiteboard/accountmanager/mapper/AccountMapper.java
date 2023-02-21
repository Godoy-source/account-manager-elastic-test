package com.whiteboard.accountmanager.mapper;

import com.codegen.rest.model.AccountResponsePresentation;
import com.codegen.rest.model.EnderecoPresentation;
import com.codegen.rest.model.NewAccountRequestPresentation;
import com.whiteboard.accountmanager.dto.AccountDTO;
import com.whiteboard.accountmanager.dto.EnderecoDTO;
import com.whiteboard.accountmanager.dto.FiltroDTO;
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

    public static HashMap<CamposBuscaEnum, String> mapearDadosEntrada(NewAccountRequestPresentation dadosEntrada) {
        var map = new HashMap<CamposBuscaEnum, String>();
        map.put(CamposBuscaEnum.CAMPO_NOME, dadosEntrada.getNome());
        map.put(CamposBuscaEnum.CAMPO_CPF, dadosEntrada.getCpf());
        map.put(CamposBuscaEnum.CAMPO_EMAIL, dadosEntrada.getEmail());
        map.put(CamposBuscaEnum.CAMPO_CEP, dadosEntrada.getEndereco().getCep());
        map.put(CamposBuscaEnum.CAMPO_RUA, dadosEntrada.getEndereco().getRua());
        map.put(CamposBuscaEnum.CAMPO_CIDADE, dadosEntrada.getEndereco().getCidade());
        map.put(CamposBuscaEnum.CAMPO_ESTADO, dadosEntrada.getEndereco().getEstado());
        map.put(CamposBuscaEnum.CAMPO_DATA_NASCIMENTO, dadosEntrada.getDataNascimento().toString());
        return map;
    }

    public static List<FiltroDTO> mapDadosEntradaToFiltrosDTO(HashMap<CamposBuscaEnum, String> dadosEntradaMapeados) {
        var camposEntradaMap = new HashSet<>(dadosEntradaMapeados.keySet());
        var filtros = new ArrayList<FiltroDTO>();

        for (var campo : camposEntradaMap) {
            filtros.add(FiltroDTO.builder()
                    .correlationEnumBusca(campo)
                    .valor(dadosEntradaMapeados.get(campo))
                    .build());
        }
        return filtros;
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
