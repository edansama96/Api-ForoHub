package com.escrituraa.Api.ForoHub.controller;


import com.escrituraa.Api.ForoHub.curso.CursoRepository;
import com.escrituraa.Api.ForoHub.perfil.DatosListaPerfil;
import com.escrituraa.Api.ForoHub.perfil.DatosPerfil;
import com.escrituraa.Api.ForoHub.perfil.Perfil;
import com.escrituraa.Api.ForoHub.perfil.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public void registrarPerfil(@RequestBody @Valid DatosPerfil perfil){
          repository.save(new Perfil(perfil));

    }

    //Mostrar método para listar los perfiles
    @GetMapping
    public List<DatosListaPerfil> listarPerfiles(){
           return repository.findAll().stream().map(DatosListaPerfil :: new).toList();
    }


}
