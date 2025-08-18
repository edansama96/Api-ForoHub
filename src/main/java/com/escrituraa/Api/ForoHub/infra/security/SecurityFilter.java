package com.escrituraa.Api.ForoHub.infra.security;

import com.escrituraa.Api.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    //Instnacia del repositorio
    @Autowired
    private UsuarioRepository repository;
    //Intancias de security para usar el método de obtener el usuario que ingresa
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener el token del cliente, se usara un método para obtener el token del envabezado de la request
        var tokenJWT = recuperarToken(request);
        System.out.println("token del encabezado: "+tokenJWT);
        if(tokenJWT != null){
        //Uso del token service
        var subject =  tokenService.getSubject(tokenJWT);
        System.out.println("El usauri que ingreso es: " + subject);
        var usuarioIngresar = repository.findByCorreoElectronico(subject);

        var authentication = new UsernamePasswordAuthenticationToken(usuarioIngresar, null, usuarioIngresar.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("usuario logueado!!");
        }
        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        //se onbtendra el token
        var authorizationHeader = request.getHeader("Authorization");
        System.out.println("El valor es: " + authorizationHeader);
        if( authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ){
            //Se procedera a quitar el prefijo con el cual se obtiene el token
            return authorizationHeader.substring(7).trim();
        }
        return null;
    }
}
