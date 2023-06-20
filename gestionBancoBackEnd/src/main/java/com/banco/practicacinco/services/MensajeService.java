package com.banco.practicacinco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.practicacinco.models.MensajeModel;
import com.banco.practicacinco.models.NotFounException;
import com.banco.practicacinco.repository.MensajeRepository;

@Service
public class MensajeService {
    @Autowired
    MensajeRepository mensajeRepository;

    public List<MensajeModel> obtenerMensajes() {
        return mensajeRepository.findAll();
    }

    public Optional<MensajeModel> obtenerMensajePorId(Long id) {
        return mensajeRepository.findById(id);
    }

    public void guardarOActualizar(MensajeModel mensaje) {
        mensajeRepository.save(mensaje);
    }

    //UPDATE
    public MensajeModel updateMensaje(Long id, MensajeModel updatedMensaje) {
        MensajeModel mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new NotFounException("Mensaje no encontrada con el id: " + id));
        mensaje.setTexto(updatedMensaje.getTexto());
        mensaje.setOrigen(updatedMensaje.getOrigen());
        mensaje.setDestino(updatedMensaje.getDestino());
        return mensajeRepository.save(mensaje);
    }

    public void eliminarPorId(Long id) {
        mensajeRepository.deleteById(id);
    }
}
