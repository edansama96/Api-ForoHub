package com.escrituraa.Api.ForoHub.usuario;

import com.escrituraa.Api.ForoHub.perfil.DatosPerfil;

public record DatosRegistroUsuario(String nombre, String correoElectronico, String contrasena, DatosPerfil datosPerfil) {
}
