package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.DatosCurso;
import com.escrituraa.Api.ForoHub.usuario.DatosRegistroUsuario;

import java.time.LocalDate;

public record DatosActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        StatusTopico status


) {
}
