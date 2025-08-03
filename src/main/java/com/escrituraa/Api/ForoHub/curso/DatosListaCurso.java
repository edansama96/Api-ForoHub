package com.escrituraa.Api.ForoHub.curso;

public record DatosListaCurso(
        String nombre,
        Categoria categoria
) {

    public DatosListaCurso(Curso curso) {
        this(
             curso.getNombre(),
             curso.getCategoria()
        );
    }
}
