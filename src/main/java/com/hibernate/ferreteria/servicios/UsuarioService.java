package com.hibernate.ferreteria.servicios;

import com.hibernate.ferreteria.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService { //UserDetailsService es una interfaz que sirve para la autenticacion del usuario en SpringSecurity

    @Autowired
    private UsuariosRepositorio repositorio;


    // Metodo obligatorio de Spring Security para buscar un usuario durante el inicio de sesión
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. Busca en la BD por el nombre de usuario.
        // Si no lo encuentra, el .orElseThrow() frena todo y lanza la excepcion de Spring Security.
        var usuario = repositorio.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // 2. Si lo encuentra, transforma tu usuario de la base de datos en un objeto "User" oficial de Spring Security.
        // Le pasa: el username, la contraseña encriptada y su lista de roles/permisos.
        return new User(
                usuario.getUsuario(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
        );
    }







}
