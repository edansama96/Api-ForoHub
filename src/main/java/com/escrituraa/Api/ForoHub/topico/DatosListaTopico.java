package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        StatusTopico statusTopico,
        String autorNombre,
        String autorCorreo,
        String cursoNombre


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
                topico.getCurso().getNombre()

        );

    }
}
