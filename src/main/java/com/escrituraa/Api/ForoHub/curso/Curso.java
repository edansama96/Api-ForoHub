package com.escrituraa.Api.ForoHub.curso;

import com.escrituraa.Api.ForoHub.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name ="Curso")
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Topico> topico;

    public Curso() {

    }

    public Curso(DatosCurso curso){
        this.id = null;
        if(curso.nombre() == null){
            throw new IllegalArgumentException("Datos del curso no pueden ser nulos");
        }else {
            this.nombre = curso.nombre();
        }
        this.categoria = curso.categoria();
        this.topico = new ArrayList<>();

    }
}
