package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.usuario.Usuario;

import java.time.LocalDate;

public class Topico {
    private Long idT;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private String status;
    private Usuario autor;
    private Curso  Curso;
    private Respuesta respuesta;
}
