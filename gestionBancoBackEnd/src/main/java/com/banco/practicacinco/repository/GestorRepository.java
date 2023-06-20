package com.banco.practicacinco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.practicacinco.models.GestorModel;

@Repository
public interface GestorRepository extends JpaRepository<GestorModel, Long> {
    
}
