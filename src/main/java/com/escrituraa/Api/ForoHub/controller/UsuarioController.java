package com.escrituraa.Api.ForoHub.controller;

import com.escrituraa.Api.ForoHub.usuario.DatosRegistroUsuario;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import com.escrituraa.Api.ForoHub.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//indica que es una clase de control
@RestController
//Indicar la URI a manejar
@RequestMapping("/usuarios")
public class UsuarioController {
    //Se establece una instancia de la interfaz repositorio de usuario
    @Autowired
    private UsuarioRepository repository;
    //Anotación para realizar modificaciones a la base de datos
    @Transactional
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public void registrar(@RequestBody DatosRegistroUsuario datos){
        repository.save(new Usuario(datos));


    }

}
