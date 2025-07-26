package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;


@Entity(name = "Topico")
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idT;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private String status;
    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Curso  curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuesta;
}
