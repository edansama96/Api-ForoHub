package com.escrituraa.Api.ForoHub.usuario;

import com.escrituraa.Api.ForoHub.perfil.Perfil;
import com.escrituraa.Api.ForoHub.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idU;

    private String nombre;
    private String correoElectronico;
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",// nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id"), // el cruce por id del usaurio
            inverseJoinColumns = @JoinColumn(name = "perfil_id")// el cruce por el id del perfil
    )
    private Set<Perfil> perfiles;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY )
    private List<Respuesta> respuestas;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Topico> topicos;


    public Usuario(DatosRegistroUsuario usuario) {
        this.idU=  null;
        this.nombre = usuario.nombre();
        this.correoElectronico = usuario.correoElectronico();
        this.contrasena = usuario.contrasena();
        this.perfiles = (Set<Perfil>) new Perfil(usuario.datosPerfil());
        // Inicializar como listas vacías si no vienen como parámetros
        this.respuestas = new ArrayList<>();
        this.topicos = new ArrayList<>();



    }
}
