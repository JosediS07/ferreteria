package com.hibernate.ferreteria.repositorios;

import com.hibernate.ferreteria.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios>findByUsuario(String usuario);
}
