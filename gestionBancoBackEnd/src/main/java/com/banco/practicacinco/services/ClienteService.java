package com.banco.practicacinco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.practicacinco.models.ClienteModel;
import com.banco.practicacinco.models.NotFounException;
import com.banco.practicacinco.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    //GET ALL
    public List<ClienteModel> obtenerClientes() {
        return clienteRepository.findAll();
    }

    //GET BY ID
    /* 
    public Optional<ClienteModel> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }
    */

    public Optional<ClienteModel> obtenerClientePorId(Long id) {
        Optional<ClienteModel> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new NotFounException("Cliente no encontrado con el id: " + id);
        }
        return clienteOptional;
    }

    //SAVE OR UPDATE
    public void guardarOActualizar(ClienteModel cliente) {
        clienteRepository.save(cliente);
    }

    //UPDATE
    public ClienteModel updateCliente(Long id, ClienteModel updatedCliente) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFounException("Cliente no encontrado con el id: " + id));
        cliente.setUsuario(updatedCliente.getUsuario());
        cliente.setPassword(updatedCliente.getPassword());
        cliente.setCorreo(updatedCliente.getCorreo());
        cliente.setSaldo(updatedCliente.getSaldo());
        cliente.setGestor(updatedCliente.getGestor());
        return clienteRepository.save(cliente);
    }

    //DELETE BY ID
    public void eliminarPorId(Long id) {
        clienteRepository.deleteById(id);
    }
}
