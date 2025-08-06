package com.escrituraa.Api.ForoHub.controller;

import com.escrituraa.Api.ForoHub.respuesta.*;
import com.escrituraa.Api.ForoHub.topico.Topico;
import com.escrituraa.Api.ForoHub.topico.TopicoRepository;
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

import java.util.List;
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
    public void registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos) {
        Topico topico = topicoRepository.findById(datos.topicoId())
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Respuesta respuesta = new Respuesta(datos, topico, autor);
        repository.save(respuesta);

    }

    //Método para listar las respuestas
    //Ya no se utilizara el Lista para  manejar la infromación
    // debido a que ahora lo que se hara es usar Pageable
    // lo que hace que se devuelva un elemento de Tipo page
    // el cual tendra la lista de la información y la dividira en páginas
    // se quito el tolist por que ya no se devuelve dicha información
    // y page tiene problemas con stream no funciona
    @GetMapping
    public Page<DatosListaRespuesta> listarRepuesta(@PageableDefault(size = 10,sort ={"mensaje"})Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListaRespuesta::new);
    }

    //Métogo para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaRespuesta>obtenerPorId(@PathVariable Long id){
        return repository.findById(id)
                .map(dr -> ResponseEntity.ok(new DatosListaRespuesta(dr)))// Si lo encuentra devuelve 200 con el objeto
                .orElse(ResponseEntity.notFound().build());// Si no existe devuelve 404
    }


    //Método para actualizar algunos elmentos
    @Transactional
    @PutMapping
    public void actualizaRespuesta(@RequestBody @Valid DatosActualizarRepuesta datos){
        //validación de que la respuesta exita y busqueda para modificar
        Optional<Respuesta> optionalRespuesta = repository.findById(datos.id());
        if(optionalRespuesta.isPresent()){
            var respuesta = optionalRespuesta.get();
            respuesta.actualizarInformaciones(datos);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Respuesta no encontrado");
        }

    }


}
