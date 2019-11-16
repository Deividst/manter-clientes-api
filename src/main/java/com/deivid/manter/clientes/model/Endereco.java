package com.deivid.manter.clientes.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa a entidade Endereco.
 * @author Deivid Thomé - 14/11/2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;


}
