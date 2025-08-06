package com.escrituraa.Api.ForoHub.usuario;

public record DatosActualizacionUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasena
) {
}
