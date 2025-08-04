package com.escrituraa.Api.ForoHub.controller;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.curso.CursoRepository;
import com.escrituraa.Api.ForoHub.curso.DatosCurso;
import com.escrituraa.Api.ForoHub.perfil.Perfil;
import com.escrituraa.Api.ForoHub.perfil.PerfilRepository;
import com.escrituraa.Api.ForoHub.usuario.DatosListaUsuarios;
import com.escrituraa.Api.ForoHub.usuario.DatosRegistroUsuario;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import com.escrituraa.Api.ForoHub.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//indica que es una clase de control
@RestController
//Indicar la URI a manejar
@RequestMapping("/usuarios")
public class UsuarioController {
    //Se establece una instancia de la interfaz repositorio de usuario
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PerfilRepository perfilRepository;
    //Anotación para realizar modificaciones a la base de datos
    @Transactional
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos){
        List<Perfil> perfiles = perfilRepository.findAllById(datos.perfilesid());
        Usuario usuario = new Usuario(datos, perfiles);
        repository.save(usuario);
    }

    //Método para mostra la lista de Usuarios
    //Ya no se utilizara el Lista para  manejar la infromación
    // debido a que ahora lo que se hara es usar Pageable
    // lo que hace que se devuelva un elemento de Tipo page
    // el cual tendra la lista de la información y la dividira en páginas
    // se quito el tolist por que ya no se devuelve dicha información
    // y page tiene problemas con stream no funciona
    @GetMapping
    public Page<DatosListaUsuarios> listarUsuarios(@PageableDefault(size = 10,sort ={"nombre"})Pageable paginacion){
         return repository.findAll(paginacion).map(DatosListaUsuarios::new);
    }

}
