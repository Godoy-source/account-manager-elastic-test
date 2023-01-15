package com.whiteboard.accountmanager.mapper;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class FuncionarioMapper extends PessoaMapper{
    private List<UUID> curso_id;
    private List<UUID> materia_id;
    private BigDecimal salario;
    private Date dataContratacao;
}
