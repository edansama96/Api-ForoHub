package com.escrituraa.Api.ForoHub.infra;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//Clase para manejar errores
//Anotación para indicar que la clase manejara errores
@RestControllerAdvice
public class GestorDeErrores {

    //Anotación para errores
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404() {
        return ResponseEntity.notFound().build();
    }

}
