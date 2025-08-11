package com.escrituraa.Api.ForoHub.domain.curso;

public record DatosListaCurso(
       Long cursoId,
        String nombre,
        Categoria categoria
) {

    public DatosListaCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
    }
}
