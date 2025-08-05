package com.escrituraa.Api.ForoHub.controller;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.curso.CursoRepository;
import com.escrituraa.Api.ForoHub.topico.*;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import com.escrituraa.Api.ForoHub.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//Indica que es una clase de control para Topico
@RestController
@RequestMapping("/topicos")
public class TopicoController {
    //Se establece una instancia de la interfaz repositorio de la clase medico
    @Autowired
    private TopicoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    //Anotación para realizar modificaciones a la base de datos
    @Transactional
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        //validación del titulo y mensaje de un nuevo topico a crear
        boolean existeTopico = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existeTopico) {
            throw new RuntimeException("Ya existe un tópico con ese título y mensaje.");
        }

        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Topico topico = new Topico(datos, autor, curso);
        repository.save(topico);


    }

    //Método para lista los topicos
    //Ya no se utilizara el Lista para  manejar la infromación
    // debido a que ahora lo que se hara es usar Pageable
    // lo que hace que se devuelva un elemento de Tipo page
    // el cual tendra la lista de la información y la dividira en páginas
    // se quito el tolist por que ya no se devuelve dicha información
    // y page tiene problemas con stream no funciona
    @GetMapping
    public Page<DatosListaTopico> listarTopicos(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        return repository.findAll(paginacion).map(lt -> new DatosListaTopico(lt));
    }

    //Método get para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopico> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(topico -> ResponseEntity.ok(new DatosListaTopico(topico)))// Si lo encuentra devuelve 200 con el objeto
                .orElse(ResponseEntity.notFound().build()); // Si no existe devuelve 404
    }





}
