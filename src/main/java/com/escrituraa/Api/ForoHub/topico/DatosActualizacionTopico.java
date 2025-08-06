package com.escrituraa.Api.ForoHub.topico;




import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosActualizacionTopico(
       @NotNull Long id,
        String titulo,
        String mensaje,
        StatusTopico status


) {
}
