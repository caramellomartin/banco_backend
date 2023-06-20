package com.banco.practicacinco.models;

public class NotFounException extends RuntimeException {
    public NotFounException(String mensaje) {
        super(mensaje);
    }
}
