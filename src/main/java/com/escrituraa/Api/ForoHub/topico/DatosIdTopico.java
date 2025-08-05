package com.escrituraa.Api.ForoHub.topico;

import java.time.LocalDate;

public record DatosIdTopico(
        Long   id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        StatusTopico statusTopico,
        String autorNombre,
        String autorCorreo,
        String cursoNombre
) {

    public DatosIdTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getAutor().getCorreoElectronico(),
                topico.getCurso().getNombre());

    }
}
