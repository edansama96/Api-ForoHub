package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.curso.DatosCurso;
import com.escrituraa.Api.ForoHub.respuesta.DatosRegistroRespuesta;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.usuario.DatosRegistroUsuario;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank  String mensaje,
        @NotNull LocalDate fechaCreacion,
        @NotNull StatusTopico status,
        //DatosRegistroUsuario datosRegistroUsuarioautor,
        @NotNull Long autorId,
        //DatosCurso datosCurso,
        @NotNull  Long cursoId,
          DatosRegistroRespuesta datosRegistroRespuesta) {
}
