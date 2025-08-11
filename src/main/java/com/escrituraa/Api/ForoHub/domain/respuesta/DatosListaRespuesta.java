package com.escrituraa.Api.ForoHub.domain.respuesta;

import java.time.LocalDate;

public record DatosListaRespuesta(
        Long id,
        String mensaje,
        String autorNombre,
        LocalDate fechaCreacion,
        boolean solucion

) {
    public DatosListaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacio(),
                respuesta.isSolucion()

        );

    }
}
