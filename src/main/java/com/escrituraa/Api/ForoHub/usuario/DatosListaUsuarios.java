package com.escrituraa.Api.ForoHub.usuario;

import com.escrituraa.Api.ForoHub.perfil.DatosListaPerfil;
import com.escrituraa.Api.ForoHub.perfil.Perfil;

import java.util.List;
import java.util.stream.Collectors;

public record DatosListaUsuarios(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasena,
        List <DatosListaPerfil> perfiles
) {
    public DatosListaUsuarios(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena(),
                usuario.getPerfiles()
                        .stream()
                        .map(DatosListaPerfil :: new)
                        .collect(Collectors.toList())

        );


    }
}
