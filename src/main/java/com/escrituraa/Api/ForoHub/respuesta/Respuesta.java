package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.Topico;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public class Respuesta {
    private Long idR;
    private String mensaje;
    private Topico topico;
    private LocalDate fechaCreacio;
    private String autor;
    private String solucion;
    private Usuario usuario;
}
