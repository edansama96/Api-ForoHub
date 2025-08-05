package com.escrituraa.Api.ForoHub.usuario;

public record DatosListaUsuarios(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasena
) {
    public DatosListaUsuarios(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena());

    }
}
