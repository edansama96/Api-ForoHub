package com.escrituraa.Api.ForoHub.domain.respuesta;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    //Método para buscar todo pero los elementos activos
    Page<Respuesta> findAllByActivoTrue(Pageable paginacion);

    //Método para buscar por id y que este activa la respuesta
    Optional<Respuesta> findByIdAndActivoTrue(Long id);
}
