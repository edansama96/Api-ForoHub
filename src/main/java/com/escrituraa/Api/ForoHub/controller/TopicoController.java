package com.escrituraa.Api.ForoHub.controller;

import com.escrituraa.Api.ForoHub.topico.DatosRegistroTopico;
import com.escrituraa.Api.ForoHub.topico.Topico;
import com.escrituraa.Api.ForoHub.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Indica que es una clase de control para Topico
@RestController
@RequestMapping("/topicos")
public class TopicoController {
    //Se establece una instancia de la interfaz repositorio de la clase medico
    @Autowired
    private TopicoRepository repository;
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public  void registrarTopico(@RequestBody DatosRegistroTopico topico){
        repository.save(new Topico(topico));
    }



}
