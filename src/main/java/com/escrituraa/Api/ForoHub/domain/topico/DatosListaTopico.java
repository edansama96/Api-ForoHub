package com.escrituraa.Api.ForoHub.domain.topico;

import com.escrituraa.Api.ForoHub.domain.curso.Categoria;

import java.time.LocalDate;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        StatusTopico statusTopico,
        String autorNombre,
        String autorCorreo,
        String cursoNombre,
        Categoria cursoCategoria


) {



    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getAutor().getCorreoElectronico(),
                topico.getCurso().getNombre(),
                topico.getCurso().getCategoria()

        );

    }
}
