package com.escrituraa.Api.ForoHub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosActualizarRepuesta(
        @NotNull  Long id,
        String mensaje,
        boolean solucion
) {
}
