package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public Topico(DatosRegistroTopico topico){
        this.idT = null;
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.fechaCreacion = topico.fechaCreacion();
        this.status = topico.status();
        this.autor = topico.autor();
        this.curso = new Curso(topico.datosCurso());
        this.respuesta = (List<Respuesta>) new Respuesta(topico.datosRegistroRespuesta());

    }



}
