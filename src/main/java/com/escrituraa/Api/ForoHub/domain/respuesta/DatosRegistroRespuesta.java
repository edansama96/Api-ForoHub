package com.escrituraa.Api.ForoHub.domain.respuesta;

import com.escrituraa.Api.ForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroRespuesta(
        @NotBlank  String mensaje,
        //DatosRegistroTopico datosRegistroTopico,
        //DatosRegistroUsuario datosRegistroUsuario;
        @NotNull  Long autorId,
        @NotNull Long topicoId,
        @NotNull LocalDate fechaCreacion, Usuario autor,
        @NotNull boolean solucion) {
}
