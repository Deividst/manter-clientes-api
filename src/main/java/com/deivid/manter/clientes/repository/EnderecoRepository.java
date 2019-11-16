package com.deivid.manter.clientes.repository;

import com.deivid.manter.clientes.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de persistencia de dados da entidade {@link Endereco}.
 * @author Deivid Thomé - 14/11/2019
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
