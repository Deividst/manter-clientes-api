package com.deivid.manter.clientes.service.impl;

import com.deivid.manter.clientes.enums.RiscoEnum;
import com.deivid.manter.clientes.model.Cliente;
import com.deivid.manter.clientes.model.Emprestimo;
import com.deivid.manter.clientes.repository.ClienteRepository;
import com.deivid.manter.clientes.repository.EnderecoRepository;
import com.deivid.manter.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

/**
 * @author Deivid
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        Optional<Cliente> obj = this.clienteRepository.findById(id);
        return obj.orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente, Long id) {
        cliente.setId(id);
        return this.clienteRepository.save(cliente);
    }

    @Override
    public Cliente delete(Long id) {
        Optional<Cliente>  clienteExcluido = this.clienteRepository.findById(id);
        this.clienteRepository.deleteById(id);
        return clienteExcluido.orElse(null);
    }

    @Override
    public Emprestimo simulacao(Emprestimo emprestimo) {
        double taxa = emprestimo.getTaxa() / 100;
        BigDecimal pv = emprestimo.getValor();

        double result = (Math.pow(1 + taxa, emprestimo.getDuracao()) * taxa)
                        / (Math.pow(1 + taxa, emprestimo.getDuracao()) - 1);

        emprestimo.setValorPrestacao(pv.multiply(new BigDecimal(result))
                .setScale(2, RoundingMode.HALF_EVEN));

        emprestimo.setTotal(emprestimo.getValorPrestacao()
                .multiply(new BigDecimal(emprestimo.getDuracao()))
                .setScale(2, RoundingMode.HALF_EVEN));
        return emprestimo;
    }

    public RiscoEnum defineRisco(Cliente cliente) {

        if (cliente.getRendimentoMensal().compareTo(new BigDecimal(8000)) > 0) {
            return RiscoEnum.A;

        } else if (cliente.getRendimentoMensal().compareTo(new BigDecimal(2000)) > 0
                && cliente.getRendimentoMensal().compareTo(new BigDecimal(8000)) <= 0) {
            return RiscoEnum.B;

        } else {
            return RiscoEnum.C;
        }
    }
}
