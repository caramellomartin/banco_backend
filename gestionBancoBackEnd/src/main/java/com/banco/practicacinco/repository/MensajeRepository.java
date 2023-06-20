package com.banco.practicacinco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.practicacinco.models.MensajeModel;

@Repository
public interface MensajeRepository extends JpaRepository<MensajeModel, Long> {
    
}
