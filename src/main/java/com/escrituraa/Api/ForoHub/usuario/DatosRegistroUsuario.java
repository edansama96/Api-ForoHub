package com.escrituraa.Api.ForoHub.usuario;

import com.escrituraa.Api.ForoHub.perfil.DatosPerfil;

import java.util.Set;

public record DatosRegistroUsuario(String nombre, String correoElectronico, String contrasena, Set<DatosPerfil> datosPerfil) {
}
