package com.escrituraa.Api.ForoHub.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Método para buscar el elemento por los usaurios estes activos
    Page<Usuario> findAllByActivoTrue(Pageable paginacion);

    //Método para buscar elementos por id y activos
   Optional<Usuario> findByIdAndActivoTrue(Long id);
}
