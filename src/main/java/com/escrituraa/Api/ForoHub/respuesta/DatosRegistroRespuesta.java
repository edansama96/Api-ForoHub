package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.DatosRegistroTopico;

import java.time.LocalDate;

public record DatosRegistroRespuesta(String mensaje, DatosRegistroTopico datosRegistroTopico, LocalDate fechaCreacion, String autor, String solucion) {
}
