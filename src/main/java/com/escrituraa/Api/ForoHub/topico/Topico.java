package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Topico")
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;
    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Curso  curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuesta;

    public Topico() {

    }

    public Topico(DatosRegistroTopico topico, Usuario autor, Curso curso){
//        this.id = null;
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.fechaCreacion = topico.fechaCreacion();
        this.status = topico.status();
        //this.autor = new Usuario(topico.datosRegistroUsuarioautor());
        this.autor = autor;
        //this.curso = new Curso(topico.datosCurso());
        this.curso = curso;
        this.respuesta = new ArrayList<>();
        if(topico.datosRegistroRespuesta()!= null) {
            this.respuesta.add(new Respuesta(topico.datosRegistroRespuesta(), this , autor));
        }

    }



}
