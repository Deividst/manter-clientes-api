package com.deivid.manter.clientes.controller;

import com.deivid.manter.clientes.event.ResourceCreatedEvent;
import com.deivid.manter.clientes.model.Cliente;
import com.deivid.manter.clientes.model.Emprestimo;
import com.deivid.manter.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Deivid
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    private ClienteService clienteService;
    private ApplicationEventPublisher publisher;

    @Autowired
    public ClienteController(ClienteService clienteService, ApplicationEventPublisher publisher) {
        this.clienteService = clienteService;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Cliente> findAll() {
        return this.clienteService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = this.clienteService.findById(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente, HttpServletResponse response) {
        Cliente clienteSalvo = this.clienteService.save(cliente);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, clienteSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok().body(this.clienteService.update(cliente, id));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Cliente cliente = this.clienteService.delete(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/simulacao")
    public ResponseEntity<Emprestimo> simulacao(@RequestBody Emprestimo emprestimo){
        Emprestimo emprestimoResult = clienteService.simulacao(emprestimo);
        return ResponseEntity.ok(emprestimoResult);
    }

}
