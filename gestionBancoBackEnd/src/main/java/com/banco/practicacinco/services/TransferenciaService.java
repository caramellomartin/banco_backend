package com.banco.practicacinco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.practicacinco.models.NotFounException;
import com.banco.practicacinco.models.TransferenciaModel;
import com.banco.practicacinco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciaRepository transferenciaRepository;

    public List<TransferenciaModel> obtenerTransferencia() {
        return transferenciaRepository.findAll();
    }

    public Optional<TransferenciaModel> obtenerTransferenciaPorId(Long id) {
        return transferenciaRepository.findById(id);
    }

    public void guardarOActualizar(TransferenciaModel transferencia) {
        transferenciaRepository.save(transferencia);
    }

    //UPDATE
    public TransferenciaModel updateTransferencia(Long id, TransferenciaModel updatedTransferencia) {
        TransferenciaModel transferencia = transferenciaRepository.findById(id)
                .orElseThrow(() -> new NotFounException("Transferencia no encontrada con el id: " + id));
        transferencia.setOrdenante(updatedTransferencia.getOrdenante());
        transferencia.setBeneficiario(updatedTransferencia.getBeneficiario());
        transferencia.setConcepto(updatedTransferencia.getConcepto());
        transferencia.setImporte(updatedTransferencia.getImporte());
        return transferenciaRepository.save(transferencia);
    }

    public void eliminarPorId(Long id) {
        transferenciaRepository.deleteById(id);
    }
}
