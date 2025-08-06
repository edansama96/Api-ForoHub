package com.escrituraa.Api.ForoHub.topico;

import com.escrituraa.Api.ForoHub.usuario.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface TopicoRepository extends JpaRepository<Topico, Long> {
    //Método utilizado para validar que el titulo y mensaje a poner no se repitan de un nuevo topico
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    //Método para buscar los usuarios que esten activos
    Page<Topico> findAllByActivoTrue(Pageable paginacion);
    //Método para buscar por id y validar que el topico este activo
    Optional<Topico> findByIdAndActivoTrue(@NotNull Long id);
}
