package com.MirasP.fichador_app.repository;

import com.MirasP.fichador_app.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailAndPasswordHash(String email, String passwordHash);
    Optional<Usuario> findByEmail(String email);
}