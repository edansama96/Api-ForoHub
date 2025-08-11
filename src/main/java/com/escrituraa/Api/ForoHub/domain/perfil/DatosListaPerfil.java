package com.escrituraa.Api.ForoHub.domain.perfil;

public record DatosListaPerfil(
        Long id,
        NombrePerfil nombre

) {
    public DatosListaPerfil(Perfil perfil) {
       this(
               perfil.getId(),
               perfil.getNombre());
    }
}
