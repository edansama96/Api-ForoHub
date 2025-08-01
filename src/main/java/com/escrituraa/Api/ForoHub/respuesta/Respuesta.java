package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.Topico;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
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
}
