package com.escrituraa.Api.ForoHub.topico;

import java.time.LocalDate;

public record DatosActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        StatusTopico statusTopico,
        String autorNombre,
        String autorCorreo,
        String cursoNombre
) {
}
