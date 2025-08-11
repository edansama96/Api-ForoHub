package com.escrituraa.Api.ForoHub.domain.usuario;

import com.escrituraa.Api.ForoHub.domain.perfil.DatosListaPerfil;

import java.util.List;
import java.util.stream.Collectors;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasena,
        List<DatosListaPerfil> perfiles
) {
    public DatosDetalleUsuario(Usuario usuario) {
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
