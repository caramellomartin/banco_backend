package com.banco.practicacinco.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.banco.practicacinco.models.MensajeModel;
import com.banco.practicacinco.services.MensajeService;

@RestController
@RequestMapping(path = "api/mensajes")
@CrossOrigin(origins = "http://localhost:4200")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;

    @GetMapping
    public List<MensajeModel> getAll() {
        return mensajeService.obtenerMensajes();
    }

    @GetMapping("/{id}")
    public Optional<MensajeModel> getById(@PathVariable("id") Long id) {
        return mensajeService.obtenerMensajePorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeModel> updateMensaje(@PathVariable Long id, @RequestBody MensajeModel updatedMensaje) {
        MensajeModel updatedMensajeModel = mensajeService.updateMensaje(id, updatedMensaje);
        return ResponseEntity.ok(updatedMensajeModel);
    }

    @PostMapping
    public void saveUpdate(@RequestBody MensajeModel mensaje) {
        if (mensaje.getOrigen().getId().equals(mensaje.getDestino().getId())) {
            throw new IllegalArgumentException("El origen y el destino no pueden ser el mismo");
        } else {
            mensajeService.guardarOActualizar(mensaje);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        mensajeService.eliminarPorId(id);
    }
}
