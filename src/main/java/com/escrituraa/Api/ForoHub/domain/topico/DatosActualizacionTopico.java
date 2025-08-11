package com.escrituraa.Api.ForoHub.domain.topico;




import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
       @NotNull Long id,
        String titulo,
        String mensaje,
        StatusTopico status


) {
}
