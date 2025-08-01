package com.escrituraa.Api.ForoHub.perfil;

import jakarta.validation.constraints.NotNull;

public record DatosPerfil(
        @NotNull  NombrePerfil nombre) {
}
