package com.escrituraa.Api.ForoHub.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Método para buscar el elemento por id

}
