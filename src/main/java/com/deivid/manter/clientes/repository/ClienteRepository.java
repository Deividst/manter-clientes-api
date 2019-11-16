package com.deivid.manter.clientes.repository;

import com.deivid.manter.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de persistencia de dados da entidade {@link Cliente}.
 * @author Deivid Thomé - 14/11/2019
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
