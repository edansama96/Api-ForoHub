package com.escrituraa.Api.ForoHub.perfil;

import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Perfil")
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    private String nombre;

    @ManyToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
    private List<Usuario> usuario;
}
