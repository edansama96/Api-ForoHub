package com.escrituraa.Api.ForoHub.respuesta;

import com.escrituraa.Api.ForoHub.topico.Topico;
import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Respuesta")
@Table(name = "respuestas")
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


    public Respuesta(DatosRegistroRespuesta respuesta) {
        this.idR = null;
        this.mensaje = respuesta.mensaje();
        this.topico = new Topico(respuesta.datosRegistroTopico());
        this.fechaCreacio = respuesta.fechaCreacion();
        this.autor =  respuesta.autor();
        this.solucion = solucion;
    }
}
