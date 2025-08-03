package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public record DatosListaTopico(
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        StatusTopico statusTopico,
        Long autorId,
        String autorNombre,
        String autorCorreo,
        Long cursoId,
        String cursoNombre


) {



    public DatosListaTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getAutor().getNombre(),
                topico.getAutor().getCorreoElectronico(),
                topico.getCurso().getId(),
                topico.getCurso().getNombre()

        );

    }
}
