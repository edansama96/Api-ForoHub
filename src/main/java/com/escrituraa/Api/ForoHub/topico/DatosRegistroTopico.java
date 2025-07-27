package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.DatosCurso;
import com.escrituraa.Api.ForoHub.respuesta.DatosRegistroRespuesta;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public record DatosRegistroTopico(String titulo, String mensaje, LocalDate fechaCreacion, String status, Usuario autor, DatosCurso datosCurso, DatosRegistroRespuesta datosRegistroRespuesta) {
}
