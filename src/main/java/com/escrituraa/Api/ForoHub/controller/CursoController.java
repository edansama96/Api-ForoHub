package com.escrituraa.Api.ForoHub.controller;


import com.escrituraa.Api.ForoHub.domain.curso.Curso;
import com.escrituraa.Api.ForoHub.domain.curso.CursoRepository;
import com.escrituraa.Api.ForoHub.domain.curso.DatosCurso;
import com.escrituraa.Api.ForoHub.domain.curso.DatosListaCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;

//indica que es una clase de control
@RestController
//Indicar la URI a manejar
@RequestMapping("/cursos")
public class CursoController {
    //Se establece una instnacia de la  interfaz repositorio de la clase medico
    @Autowired
    private CursoRepository repository;
    //Anotación para realizar modificaciones a la base de datos
    @Transactional
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public ResponseEntity registrarCurso(@RequestBody @Valid DatosCurso datos, UriComponentsBuilder uriComponentsBuilder) {
       var curso = new Curso(datos);
        repository.save(curso);
        //se establece la uri para la respuesta a manejar
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso).toUri();
        return  ResponseEntity.created(uri).body(new DatosListaCurso(curso));

    }
    //Métogo para listar los cursos
    @GetMapping
    public ResponseEntity<List<DatosListaCurso>> listaCursos(){
       var  cursos= repository.findAll().stream().map(dc -> new DatosListaCurso(dc)).toList();
        return  ResponseEntity.ok(cursos);
    }

    //Método para listar por ID
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaCurso> buscarPorId(@PathVariable Long id){
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
        var curso = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(new DatosListaCurso(curso));

    }



}

