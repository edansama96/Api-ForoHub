package com.escrituraa.Api.ForoHub.perfil;

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
