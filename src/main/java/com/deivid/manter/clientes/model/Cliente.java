package com.deivid.manter.clientes.model;

import com.deivid.manter.clientes.converter.RiscoEnumConverter;
import com.deivid.manter.clientes.enums.RiscoEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade Cliente.
 * @author Deivid Thomé - 14/11/2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private BigDecimal rendimentoMensal;
    @Convert(converter = RiscoEnumConverter.class)
    private RiscoEnum risco;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

}
