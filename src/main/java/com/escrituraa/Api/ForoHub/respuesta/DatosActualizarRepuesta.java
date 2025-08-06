package com.escrituraa.Api.ForoHub.respuesta;

import java.time.LocalDate;

public record DatosActualizarRepuesta(
        Long id,
        String mensaje,
        boolean solucion
) {
}
