package com.escrituraa.Api.ForoHub.respuesta;

import java.time.LocalDate;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        String autorNombre,
        LocalDate fechaCreacion,
        boolean solucion

) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacio(),
                respuesta.isSolucion()
        );

    }
}
