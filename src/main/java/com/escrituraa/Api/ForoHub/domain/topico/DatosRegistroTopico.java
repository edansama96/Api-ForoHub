package com.escrituraa.Api.ForoHub.domain.topico;

import com.escrituraa.Api.ForoHub.domain.respuesta.DatosRegistroRespuesta;
import jakarta.validation.constraints.NotBlank;
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
