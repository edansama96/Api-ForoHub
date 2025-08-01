package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.curso.DatosCurso;
import com.escrituraa.Api.ForoHub.respuesta.DatosRegistroRespuesta;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.usuario.DatosRegistroUsuario;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public record DatosRegistroTopico(
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        StatusTopico status,
        //DatosRegistroUsuario datosRegistroUsuarioautor,
        Long autorId,
        //DatosCurso datosCurso,
        Long cursoId,
        DatosRegistroRespuesta datosRegistroRespuesta) {
}
