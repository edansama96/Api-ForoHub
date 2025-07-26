package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.Topico;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.List;

public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idR;
    private String mensaje;
    @ManyToOne
    private Topico topico;
    private LocalDate fechaCreacio;
    @ManyToOne
    private Usuario autor;
    private String solucion;
}
