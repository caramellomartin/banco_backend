package com.banco.practicacinco.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.banco.practicacinco.models.ClienteModel;
import com.banco.practicacinco.models.NotFounException;
import com.banco.practicacinco.services.ClienteService;

@RestController
@RequestMapping(path = "api/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteModel> getAll() {
        return clienteService.obtenerClientes();
    }

    @GetMapping("/{id}")
    public Optional<ClienteModel> getById(@PathVariable("id") Long id) {
        try {
            Optional<ClienteModel> clienteOptional = clienteService.obtenerClientePorId(id);
            return clienteOptional;
        } catch (NotFounException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void saveUpdate(@RequestBody ClienteModel cliente) {
        clienteService.guardarOActualizar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long id, @RequestBody ClienteModel updatedCliente) {
        ClienteModel updatedClienteModel = clienteService.updateCliente(id, updatedCliente);
        return ResponseEntity.ok(updatedClienteModel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        clienteService.eliminarPorId(id);
    }
}
