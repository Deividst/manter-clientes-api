package com.deivid.manter.clientes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    private BigDecimal valor;
    private Integer duracao;
    private double taxa;
    private BigDecimal total;
    private BigDecimal valorPrestacao;

}
