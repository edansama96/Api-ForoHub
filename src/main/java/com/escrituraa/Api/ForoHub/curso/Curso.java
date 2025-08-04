package com.escrituraa.Api.ForoHub.curso;

import com.escrituraa.Api.ForoHub.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
//@Getter
//@Setter
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
        //this.id = null;
        if(curso.nombre() == null){
            throw new IllegalArgumentException("Datos del curso no pueden ser nulos");
        }else {
            this.nombre = curso.nombre();
        }
        this.categoria = curso.categoria();
        this.topico = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Topico> getTopico() {
        return topico;
    }

    public void setTopico(List<Topico> topico) {
        this.topico = topico;
    }
}
