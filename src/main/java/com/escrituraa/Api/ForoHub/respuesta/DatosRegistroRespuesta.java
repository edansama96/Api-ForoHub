package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.DatosRegistroTopico;
import com.escrituraa.Api.ForoHub.usuario.DatosRegistroUsuario;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public record DatosRegistroRespuesta(
        String mensaje,
        //DatosRegistroTopico datosRegistroTopico,
        //DatosRegistroUsuario datosRegistroUsuario;
        Long autorId,
        Long topicoId,
        LocalDate fechaCreacion, Usuario autor,
        boolean solucion) {
}
