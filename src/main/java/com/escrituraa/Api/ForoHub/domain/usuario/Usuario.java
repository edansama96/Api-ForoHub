package com.escrituraa.Api.ForoHub.domain.usuario;

import com.escrituraa.Api.ForoHub.domain.perfil.Perfil;
import com.escrituraa.Api.ForoHub.domain.respuesta.Respuesta;
import com.escrituraa.Api.ForoHub.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean activo ;
    private String nombre;
    private String correoElectronico;
    private String contrasena;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuarios_perfiles",// nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id"), // el cruce por id del usaurio
            inverseJoinColumns = @JoinColumn(name = "perfil_id")// el cruce por el id del perfil
    )
    private List<Perfil> perfiles;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY )
    private List<Respuesta> respuestas;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Topico> topicos;

    public Usuario() {

    }

    public Usuario(DatosRegistroUsuario usuario, List<Perfil> perfiles) {
        //this.id=  null;
        this.activo = true;
        this.nombre = usuario.nombre();
        this.correoElectronico = usuario.correoElectronico();
        this.contrasena = usuario.contrasena();
        this.perfiles = perfiles;
        // Inicializar como listas vacías si no vienen como parámetros
        this.respuestas = new ArrayList<>();
        this.topicos = new ArrayList<>();



    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }



    public void actualizarInformaciones(@Valid DatosActualizacionUsuario datos) {
       if(datos.nombre() != null){
           this.nombre = datos.nombre();
       }

       if(datos.correoElectronico() != null){
           this.correoElectronico = datos.correoElectronico();

       }

       if(datos.contrasena() != null){
           this.contrasena = datos.contrasena();
       }

    }

    public void eliminiarUsuario() {
        this.activo = false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return perfiles.stream()
                .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getNombre().name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
