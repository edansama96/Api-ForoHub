package com.escrituraa.Api.ForoHub.controller;


import com.escrituraa.Api.ForoHub.curso.CursoRepository;
import com.escrituraa.Api.ForoHub.perfil.DatosPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//indica que es una clase de control
@RestController
//Indicar la URI a manejar
@RequestMapping("/perfil")
public class PerfilController {
    //Instnaica de la clase repositorio de Perfil
    @Autowired
    private CursoRepository repository;
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public void registrarPerfil(@RequestBody DatosPerfil perfil){

    }


}
