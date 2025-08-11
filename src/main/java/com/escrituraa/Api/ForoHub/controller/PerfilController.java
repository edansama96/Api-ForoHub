package com.escrituraa.Api.ForoHub.controller;


import com.escrituraa.Api.ForoHub.domain.perfil.DatosListaPerfil;
import com.escrituraa.Api.ForoHub.domain.perfil.DatosPerfil;
import com.escrituraa.Api.ForoHub.domain.perfil.Perfil;
import com.escrituraa.Api.ForoHub.domain.perfil.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

//indica que es una clase de control
@RestController
//Indicar la URI a manejar
@RequestMapping("/perfiles")
public class PerfilController {
    //Instnaica de la clase repositorio de Perfil
    @Autowired
    private PerfilRepository repository;
    //Anotación para realizar modificaciones a la base de datos
    @Transactional
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public ResponseEntity registrarPerfil(@RequestBody @Valid DatosPerfil datos, UriComponentsBuilder uriComponentsBuilder){
        var perfil =  new Perfil(datos);
        repository.save(perfil);
            // se establece la uri a manejar
        var uri = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        //el retonor de elemento dto
        return ResponseEntity.created(uri).body(new DatosListaPerfil(perfil));
    }

    //Mostrar método para listar los perfiles
    @GetMapping
    public ResponseEntity<List<DatosListaPerfil>> listarPerfiles(){
           var perfil = repository.findAll().stream().map(DatosListaPerfil :: new).toList();
        return  ResponseEntity.ok(perfil);
    }

    //Método para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaPerfil> buscarPorId(@PathVariable Long id){
        /*
         * Primera forma (comentada):
         * Manejo manual del Optional:
         * - Si el curso existe → lo convierte a DatosListaCurso y devuelve 200 OK.
         * - Si no existe → devuelve 404 Not Found directamente.
         *
         * return repository.findById(id)
         *         .map(dc -> ResponseEntity.ok(new DatosListaCurso(dc))) // Encontrado → 200 OK
         *         .orElse(ResponseEntity.notFound().build());            // No encontrado → 404
         */

        /*
         * Segunda forma (implementada):
         * - Si el curso existe → se devuelve como DatosListaCurso con 200 OK.
         * - Si no existe → se lanza EntityNotFoundException.
         *   Esta excepción será capturada por un @RestControllerAdvice
         *   para devolver 404 Not Found de forma centralizada.
         */
        var perfil = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DatosListaPerfil(perfil));

    }

}
