package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.DatosRegistroTopico;
import com.escrituraa.Api.ForoHub.usuario.DatosRegistroUsuario;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
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
