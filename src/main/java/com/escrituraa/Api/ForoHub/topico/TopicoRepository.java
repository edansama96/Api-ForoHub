package com.escrituraa.Api.ForoHub.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface TopicoRepository extends JpaRepository<Topico, Long> {



}
