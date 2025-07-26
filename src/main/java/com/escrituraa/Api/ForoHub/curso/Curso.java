package com.escrituraa.Api.ForoHub.curso;

import com.escrituraa.Api.ForoHub.topico.Topico;
import jakarta.persistence.*;

import java.util.List;

@Entity(name ="Curso")
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Topico> topico;
}
