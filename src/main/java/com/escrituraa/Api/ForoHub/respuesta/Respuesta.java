package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.Topico;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;


//@Getter
//@Setter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    private Topico topico;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacio;
    @ManyToOne
    private Usuario autor;
    private boolean solucion;

    public Respuesta() {

    }

    public Respuesta(DatosRegistroRespuesta respuesta, Topico topico, Usuario autor) {
        //this.id = null;
        this.mensaje = respuesta.mensaje();
        this.topico = topico;
        this.fechaCreacio = respuesta.fechaCreacion();
        this.autor =  autor;
        this.solucion = respuesta.solucion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDate getFechaCreacio() {
        return fechaCreacio;
    }

    public void setFechaCreacio(LocalDate fechaCreacio) {
        this.fechaCreacio = fechaCreacio;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public boolean isSolucion() {
        return solucion;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }

    public void actualizarInformaciones(@Valid DatosActualizarRepuesta datos) {
    if(datos.mensaje() != null){
        this.mensaje = datos.mensaje();

    }
        this.solucion= datos.solucion();


    }
}
