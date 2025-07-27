package com.escrituraa.Api.ForoHub.perfil;

import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Perfil")
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    private String nombre;

    @ManyToMany(mappedBy = "perfiles", fetch = FetchType.LAZY)
    private List<Usuario> usuario;

    public Perfil(DatosPerfil perfil){
        this.idP = null;
        this.nombre = perfil.nombre();

    }



}
