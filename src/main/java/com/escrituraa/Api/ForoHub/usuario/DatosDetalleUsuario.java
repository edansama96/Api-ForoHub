package com.escrituraa.Api.ForoHub.usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasena
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena()
        );

    }
}
