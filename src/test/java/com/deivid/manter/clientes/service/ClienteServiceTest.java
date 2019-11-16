package com.deivid.manter.clientes.service;

import com.deivid.manter.clientes.model.Cliente;
import com.deivid.manter.clientes.model.Emprestimo;
import com.deivid.manter.clientes.model.Endereco;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

/**
 * @author Deivid
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldSaveCliente(){
        Cliente cliente = createCliente();
        cliente = this.clienteService.save(cliente);
        Assertions.assertThat(cliente.getId()).isNotNull();
    }

    @Test
    public void shouldFindAllCliente(){
        List<Cliente> clientes = this.clienteService.findAll();
        Assert.assertTrue(clientes == null || !clientes.isEmpty());
    }

    @Test
    public void shouldFindClienteById(){
        Cliente cliente = createCliente();
        cliente = this.clienteService.save(cliente);

        Cliente result = this.clienteService.findById(cliente.getId());
        Assertions.assertThat(result.getId()).isNotNull();
    }

    @Test
    public void shouldUpdateCliente(){
        Cliente cliente = createCliente();
        cliente = this.clienteService.save(cliente);

        cliente.setNome("TesteEditar");
        cliente = this.clienteService.update(cliente, cliente.getId());

        Assertions.assertThat("TesteEditar").isEqualTo(cliente.getNome());
    }

    @Test
    public void shouldDeleteCliente(){
        Cliente cliente = createCliente();
        cliente = this.clienteService.save(cliente);

        cliente = this.clienteService.delete(cliente.getId());
        Assertions.assertThat(cliente).isNotNull();
    }

    @Test
    public void shouldSimulacao(){
        Emprestimo emp = new Emprestimo();
        emp.setValor(new BigDecimal(10000));
        emp.setDuracao(10);
        emp.setTaxa(2);

        emp = this.clienteService.simulacao(emp);

        Assertions.assertThat(emp.getTotal()).isEqualTo(new BigDecimal(11132.70).setScale(2, RoundingMode.HALF_EVEN));
        Assertions.assertThat(emp.getValorPrestacao()).isEqualTo(new BigDecimal(1113.27).setScale(2, RoundingMode.HALF_EVEN));
    }

    private Cliente createCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("teste" + new Random().nextInt(100));
        cliente.setRendimentoMensal(new BigDecimal(new Random().nextInt(10000)));
        cliente.setRisco(this.clienteService.defineRisco(cliente));
        cliente.setEndereco(createEndereco());

        return cliente;
    }

    private Endereco createEndereco(){
        Endereco endereco = new Endereco();
        endereco.setNumero(new Random().nextInt(100));
        endereco.setBairro("Algum bairro" + new Random().nextInt(100));
        endereco.setCidade("Alguma Cidade" + new Random().nextInt(100));
        endereco.setCep("12345678");
        endereco.setEstado("RS");

        return endereco;
    }

}
