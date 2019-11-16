package com.deivid.manter.clientes.service;

import com.deivid.manter.clientes.enums.RiscoEnum;
import com.deivid.manter.clientes.model.Cliente;
import com.deivid.manter.clientes.model.Emprestimo;

import java.util.List;

/**
 * @author Deivid
 */
public interface ClienteService {

    List<Cliente> findAll();

    Cliente findById(Long id);

    Cliente save(Cliente cliente);

    Cliente update(Cliente cliente, Long id);

    Cliente delete(Long id);

    RiscoEnum defineRisco(Cliente cliente);

    Emprestimo simulacao(Emprestimo emprestimo);
}
