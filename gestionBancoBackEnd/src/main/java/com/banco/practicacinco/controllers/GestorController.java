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

import com.banco.practicacinco.models.GestorModel;
import com.banco.practicacinco.models.NotFounException;
import com.banco.practicacinco.services.GestorService;

@RestController
@RequestMapping(path = "api/gestores")
@CrossOrigin(origins = "http://localhost:4200")
public class GestorController {

    //Autowired para enlazar los servicios
    @Autowired
    private GestorService gestorService;

    @GetMapping
    public List<GestorModel> getAll() {
        return gestorService.obtenerGestores();
    }

    @GetMapping("/{id}")
    public Optional<GestorModel> getById(@PathVariable("id") Long id) {
        try {
            Optional<GestorModel> gestorOptional = gestorService.obtenerGestorPorId(id);
            return gestorOptional;
        } catch (NotFounException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void saveUpdate(@RequestBody GestorModel gestor) {
        gestorService.guardarOActualizar(gestor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestorModel> updateGestor(@PathVariable Long id, @RequestBody GestorModel updatedGestor) {
        GestorModel updatedGestorModel = gestorService.updateGestor(id, updatedGestor);
        return ResponseEntity.ok(updatedGestorModel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        gestorService.eliminarPorId(id);
    }
}
