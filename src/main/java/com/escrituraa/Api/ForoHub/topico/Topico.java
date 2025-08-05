package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.curso.Curso;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public void setStatus(StatusTopico status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Respuesta> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<Respuesta> respuesta) {
        this.respuesta = respuesta;
    }

    public void actualizarInformaciones(@Valid DatosActualizacionTopico datos) {
        if(datos.titulo()!= null){
            this.titulo = datos.titulo();
        }

        if(datos.mensaje()!= null){
            this.mensaje = datos.mensaje();
        }

        if(datos.status() != null){
            this.status = datos.status();
        }




    }
}
