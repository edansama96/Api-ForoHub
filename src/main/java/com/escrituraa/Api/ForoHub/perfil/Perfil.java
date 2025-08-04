package com.escrituraa.Api.ForoHub.perfil;

import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Perfil")
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombrePerfil nombre;

    @ManyToMany(mappedBy = "perfiles", fetch = FetchType.LAZY)
    private List<Usuario> usuario;

    public Perfil(){

    }

    public Perfil(DatosPerfil perfil){
        //this.id = null;
        this.nombre = perfil.nombre();

    }
    public Perfil(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NombrePerfil getNombre() {
        return nombre;
    }

    public void setNombre(NombrePerfil nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }
}
