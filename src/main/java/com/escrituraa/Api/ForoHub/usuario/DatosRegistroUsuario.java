package com.escrituraa.Api.ForoHub.usuario;

import com.escrituraa.Api.ForoHub.perfil.DatosPerfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record DatosRegistroUsuario(
        @NotBlank String nombre,
        @NotBlank @Email String correoElectronico,
        @NotBlank String contrasena,
        @NotEmpty @Valid List<Long> perfilesid ) {
}
