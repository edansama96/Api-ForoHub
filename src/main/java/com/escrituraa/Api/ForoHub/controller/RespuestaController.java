package com.escrituraa.Api.ForoHub.controller;

import com.escrituraa.Api.ForoHub.domain.respuesta.*;
import com.escrituraa.Api.ForoHub.domain.topico.Topico;
import com.escrituraa.Api.ForoHub.domain.topico.TopicoRepository;
import com.escrituraa.Api.ForoHub.domain.usuario.Usuario;
import com.escrituraa.Api.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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

import java.util.Optional;

//indica que es una clase de control
@RestController
//Indicar la URI a manejar
@RequestMapping("/respuestas")
public class RespuestaController {
    //Se establece una instnacia de la  interfaz repositorio de la clase medico
    @Autowired
    private RespuestaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    //Anotación para realizar modificaciones a la base de datos
    @Transactional
    //Método para usar un verbo de http en espécifico,
    //para realizar una acción puntual
    //anotación para indicar que se registraran los medicos
    @PostMapping
    //Proceso para recibir los datos
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.findById(datos.topicoId())
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Respuesta respuesta = new Respuesta(datos, topico, autor);
        repository.save(respuesta);
        //Se establece el uso de la calse uriComponentsBuilder para el manejo de ciertos datos del servidor
        // en donde se estableca la api rest sea local o desplegado
        var uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        //Se destaca que no se devulve un elemento JPA si no elementos DTO
        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));

    }

    //Método para listar las respuestas
    //Ya no se utilizara el Lista para  manejar la infromación
    // debido a que ahora lo que se hara es usar Pageable
    // lo que hace que se devuelva un elemento de Tipo page
    // el cual tendra la lista de la información y la dividira en páginas
    // se quito el tolist por que ya no se devuelve dicha información
    // y page tiene problemas con stream no funciona
    @GetMapping
    public ResponseEntity<Page<DatosListaRespuesta>> listarRepuesta(@PageableDefault(size = 10,sort ={"mensaje"})Pageable paginacion){
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaRespuesta::new);
        return  ResponseEntity.ok(page);
    }

    //Métogo para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaRespuesta>obtenerPorId(@PathVariable Long id){
        /*
         * Primera forma (comentada):
         * Manejo manual del Optional:
         * - Si el curso existe → lo convierte a DatosListaCurso y devuelve 200 OK.
         * - Si no existe → devuelve 404 Not Found directamente.
         *
        return repository.findByIdAndActivoTrue(id)
                .map(dr -> ResponseEntity.ok(new DatosListaRespuesta(dr)))// Si lo encuentra devuelve 200 con el objeto
                .orElse(ResponseEntity.notFound().build());// Si no existe devuelve 404
    */
        /*
         * Segunda forma (implementada):
         * - Si el curso existe → se devuelve como DatosListaCurso con 200 OK.
         * - Si no existe → se lanza EntityNotFoundException.
         *   Esta excepción será capturada por un @RestControllerAdvice
         *   para devolver 404 Not Found de forma centralizada.
         */
        var respuesta = repository.findByIdAndActivoTrue(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DatosListaRespuesta(respuesta));

    }


    //Método para actualizar algunos elmentos
    @Transactional
    @PutMapping
    public ResponseEntity actualizaRespuesta(@RequestBody @Valid DatosActualizarRepuesta datos){
        //validación de que la respuesta exita y busqueda para modificar
        Optional<Respuesta> optionalRespuesta = repository.findByIdAndActivoTrue(datos.id());
        if(optionalRespuesta.isPresent()){
            var respuesta = optionalRespuesta.get();
            respuesta.actualizarInformaciones(datos);
            return  ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Respuesta no encontrado");
        }

    }

    //Método para el delete o deshabilitar las respuestas
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        var respuesta = repository.getReferenceById(id);
        respuesta.eliminarRespuesta();
        return  ResponseEntity.noContent().build();
    }


}
