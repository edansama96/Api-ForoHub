package com.escrituraa.Api.ForoHub.controller;


import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.curso.CursoRepository;
import com.escrituraa.Api.ForoHub.curso.DatosCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void registrarCurso(@RequestBody DatosCurso curso) {
        repository.save(new Curso(curso));

    }
}
