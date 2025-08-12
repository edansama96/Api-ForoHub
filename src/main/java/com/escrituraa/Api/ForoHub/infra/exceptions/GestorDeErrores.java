package com.escrituraa.Api.ForoHub.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    //Método para manejar errores por parametros ingresados mal o vacios
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException ex){
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
    }

    //Método record para mostrar el error y lo que paso
    public record DatosErrorValidacion(String campo, String mensaje){

        public DatosErrorValidacion(FieldError error){
            this(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
    }

}
