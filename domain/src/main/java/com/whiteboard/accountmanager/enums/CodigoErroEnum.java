package com.whiteboard.accountmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodigoErroEnum {


    ERRO_INTERO("Ocorreu um erro interno.", 500),
    ERRO_CADASTRO_NAO_EFETIVADO("Ocorreu um erro ao registrar conta.", 500),
    ERRO_DADOS_ENTRADA_NAO_PERMITIDOS("O caracter (@character) no campo (@campo) não é permitido como um dado de entrada.", 400),
    ERRO_DADOS_ENTRADA_TAMANHO_CAMPO("O campo (@campo) não pode ser menor de (@limiteMin) e ser maior de (@limiteMax) caracteres", 400),
    ERRO_DADOS_ENTRADA_VALOR_INVALIDO("O campo (@campo) não pode conter letras.", 400),
    ERRO_DADOS_ENTRADA_NULO("O campo (@campo) é obrigatório.", 400),
    EROO_DADOS_ENTRADA_CARACTER_NECESSARIO("Email inválido.", 400),
    ERRO_CONTA_ANTERIORMENTE_REGISTRADA("Os dados dessa conta ja se encontram nos nossos registros.", 400),
    ERRO_CONTA_NAO_ENCONTRADA("Não foi possivel encontrar os dados da conta.", 404),
    ERRO_STATUS_NAO_ENCONTRADO("Existem somente 3 tipos de status para uma conta, são eles: A | B | D", 400),
    ERRO_FILTRO_NAO_ENCONTRADO("O Filtro requisitado não esta mapeado", 400);
    private String descricaoCodigo;
    private Integer codigoHttp;
}
