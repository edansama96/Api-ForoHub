package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.DatosCurso;
import com.escrituraa.Api.ForoHub.respuesta.DatosRegistroRespuesta;

import java.time.LocalDate;

public record DatosRegistroTopico(String titulo, String mensaje, LocalDate fechaCreacion, String status, DatosCurso datosCurso, DatosRegistroRespuesta datosRegistroRespuesta) {
}
