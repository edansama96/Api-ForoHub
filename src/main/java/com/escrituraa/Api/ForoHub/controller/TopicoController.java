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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
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
        //Se establece el uso de la instnacia de la clase  UriComponentsBuilder para entender y usar ciertos datos del servidor
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        //Se envia elemento creado devuelta de tipo DTO
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));

    }

    //Método para lista los topicos
    //Ya no se utilizara el Lista para  manejar la infromación
    // debido a que ahora lo que se hara es usar Pageable
    // lo que hace que se devuelva un elemento de Tipo page
    // el cual tendra la lista de la información y la dividira en páginas
    // se quito el tolist por que ya no se devuelve dicha información
    // y page tiene problemas con stream no funciona
    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion).map(lt -> new DatosListaTopico(lt));
        return ResponseEntity.ok(page);
    }

    //Método get para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopico> obtenerPorId(@PathVariable Long id) {
        return repository.findByIdAndActivoTrue(id)
                .map(topico -> ResponseEntity.ok(new DatosListaTopico(topico)))// Si lo encuentra devuelve 200 con el objeto
                .orElse(ResponseEntity.notFound().build()); // Si no existe devuelve 404
    }

    //Método para actualizar con put el topico
    @Transactional
    @PutMapping
    public ResponseEntity actualizacionTopico(@RequestBody @Valid DatosActualizacionTopico datos){
        //obtener el topido por id
//        var topico = repository.getReferenceById(datos.id());
//        topico.actualizarInformaciones(datos);
        //validación del topico
        Optional<Topico> optionalTopico = repository.findByIdAndActivoTrue(datos.id());
        if(optionalTopico.isPresent()){
            var topico = optionalTopico.get();
            topico.actualizarInformaciones(datos);
            return  ResponseEntity.ok(new DatosDetalleTopico(topico));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }

    }

    //Método para eliminar o en el presente caso deshabilitar los usuarios
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.eliminarTopico();
        return  ResponseEntity.noContent().build();
    }


}
