package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.curso.Categoria;
import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.perfil.Perfil;
import com.escrituraa.Api.ForoHub.topico.StatusTopico;

import java.time.LocalDate;

public record DatosListaRespuesta(
        String mensaje,
        String autorNombre,
        LocalDate fechaCreacion,
        boolean solucion

) {
    public DatosListaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacio(),
                respuesta.isSolucion()

        );

    }
}
