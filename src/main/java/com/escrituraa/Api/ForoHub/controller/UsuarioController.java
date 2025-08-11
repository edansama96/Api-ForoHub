package com.escrituraa.Api.ForoHub.controller;

import com.escrituraa.Api.ForoHub.domain.perfil.Perfil;
import com.escrituraa.Api.ForoHub.domain.perfil.PerfilRepository;
import com.escrituraa.Api.ForoHub.domain.usuario.*;
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

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        List<Perfil> perfiles = perfilRepository.findAllById(datos.perfilesid());
        Usuario usuario = new Usuario(datos, perfiles);
        repository.save(usuario);
       //Se establece la uri que se manejara
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
       // no se devuelve un jpa si no un dto usuario
       return  ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    //Método para mostra la lista de Usuarios
    //Ya no se utilizara el Lista para  manejar la infromación
    // debido a que ahora lo que se hara es usar Pageable
    // lo que hace que se devuelva un elemento de Tipo page
    // el cual tendra la lista de la información y la dividira en páginas
    // se quito el tolist por que ya no se devuelve dicha información
    // y page tiene problemas con stream no funciona
    @GetMapping
    public ResponseEntity<Page<DatosListaUsuarios>> listarUsuarios(@PageableDefault(size = 10,sort ={"nombre"})Pageable paginacion){
         var page = repository.findAllByActivoTrue(paginacion).map(DatosListaUsuarios::new);
        return  ResponseEntity.ok(page);
    }

    //Método para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaUsuarios> buscarPoId(@PathVariable Long id){
       /*
       * Primera forma (comentada):
         * Manejo manual del Optional:
         * - Si el curso existe → lo convierte a DatosListaCurso y devuelve 200 OK.
         * - Si no existe → devuelve 404 Not Found directamente.
         *
        return  repository.findByIdAndActivoTrue(id)
                .map(du -> ResponseEntity.ok(new DatosListaUsuarios(du)))// Si lo encuentra devuelve 200 con el objeto
                .orElse(ResponseEntity.notFound().build());// Si no existe devuelve 404
        */
        /*
         * Segunda forma (implementada):
         * - Si el curso existe → se devuelve como DatosListaCurso con 200 OK.
         * - Si no existe → se lanza EntityNotFoundException.
         *   Esta excepción será capturada por un @RestControllerAdvice
         *   para devolver 404 Not Found de forma centralizada.
         */
        var usuario = repository.findByIdAndActivoTrue(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DatosListaUsuarios(usuario));



    }

    //Método para actualizar con put el topico
    @Transactional
    @PutMapping
    public ResponseEntity actualizacionUsuario(@RequestBody @Valid DatosActualizacionUsuario datos){
        //obtener el usuario por id
//        var usuario = repository.getReferenceById(datos.id());
//        usuario.actualizarInformaciones(datos);
        //Validación de que el usaurio exista y permitir la busqueda por id
        Optional<Usuario> optionalUsuario = repository.findByIdAndActivoTrue(datos.id());
        if(optionalUsuario.isPresent()){
            var usuario = optionalUsuario.get();
            usuario.actualizarInformaciones(datos);
            return  ResponseEntity.ok(new DatosDetalleUsuario(usuario));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

    }

    //Método para eliminar o deshabilitar por id
    @Transactional
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.eliminiarUsuario();
    }

}
