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

import com.banco.practicacinco.models.TransferenciaModel;
import com.banco.practicacinco.services.TransferenciaService;

@RestController
@RequestMapping(path = "api/transferencias")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public List<TransferenciaModel> getAll() {
        return transferenciaService.obtenerTransferencia();
    }

    @GetMapping("/{id}")
    public Optional<TransferenciaModel> getById(@PathVariable("id") Long id) {
        return transferenciaService.obtenerTransferenciaPorId(id);
    }

    @PostMapping
    public void saveUpdate(@RequestBody TransferenciaModel transferencia) {
        if (transferencia.getOrdenante().getId().equals(transferencia.getBeneficiario().getId())) {
            throw new IllegalArgumentException("El ordenante y el beneficiario no pueden ser el mismo");
        } else {
            transferenciaService.guardarOActualizar(transferencia);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferenciaModel> updateTransferencia(@PathVariable Long id, @RequestBody TransferenciaModel updatedTransferencia) {
        TransferenciaModel updatedTransferenciaModel = transferenciaService.updateTransferencia(id, updatedTransferencia);
        return ResponseEntity.ok(updatedTransferenciaModel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        transferenciaService.eliminarPorId(id);
    }
}
