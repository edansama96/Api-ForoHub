package com.escrituraa.Api.ForoHub.usuario;

public record DatosListaUsuarios(

        String nombre,
        String correoElectronico,
        String contrasena
) {
    public DatosListaUsuarios(Usuario usuario) {
        this(
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena());

    }
}
