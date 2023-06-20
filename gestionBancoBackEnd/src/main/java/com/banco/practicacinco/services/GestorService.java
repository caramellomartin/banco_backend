package com.banco.practicacinco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.practicacinco.models.GestorModel;
import com.banco.practicacinco.models.NotFounException;
import com.banco.practicacinco.repository.GestorRepository;

@Service
public class GestorService {
    //Inyección
    @Autowired
    GestorRepository gestorRepository;

    //GET ALL
    public List<GestorModel> obtenerGestores() {
        return gestorRepository.findAll();
    }

    //GET BY ID
    /*
    public Optional<GestorModel> obtenerGestorPorId(Long id) {
        return gestorRepository.findById(id);
    }*/
    public Optional<GestorModel> obtenerGestorPorId(Long id) {
        Optional<GestorModel> gestorOptional = gestorRepository.findById(id);
        if (gestorOptional.isEmpty()) {
            throw new NotFounException("Gestor not found with id: " + id);
        }
        return gestorOptional;
    }

    //SAVE OR UPDATE
    public void guardarOActualizar(GestorModel gestor) {
        gestorRepository.save(gestor);
    }

    //UPDATE
    public GestorModel updateGestor(Long id, GestorModel updatedGestor) {
        GestorModel gestor = gestorRepository.findById(id)
                .orElseThrow(() -> new NotFounException("Gestor no encontrado con el id: " + id));
        gestor.setUsuario(updatedGestor.getUsuario());
        gestor.setPassword(updatedGestor.getPassword());
        gestor.setCorreo(updatedGestor.getCorreo());

        return gestorRepository.save(gestor);
    }

    //DELETE BY ID
    public void eliminarPorId(Long id) {
        gestorRepository.deleteById(id);
    }
}
