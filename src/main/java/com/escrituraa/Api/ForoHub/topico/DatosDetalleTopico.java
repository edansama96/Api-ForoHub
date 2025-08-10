package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        StatusTopico statusTopico,
        Usuario autor,
        Curso curso

) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );

    }
}
