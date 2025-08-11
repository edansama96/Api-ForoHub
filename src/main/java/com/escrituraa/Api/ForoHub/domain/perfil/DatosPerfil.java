package com.escrituraa.Api.ForoHub.domain.perfil;

import jakarta.validation.constraints.NotNull;

public record DatosPerfil(
        @NotNull  NombrePerfil nombre) {
}
