package com.MirasP.fichador_app.service;

import com.MirasP.fichador_app.entity.Usuario;
import com.MirasP.fichador_app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para registrar usuario
    public boolean registrar(String nombre, String email, String password) {
        // Comprobar si email ya existe
        if (usuarioRepository.findByEmail(email).isPresent()) {
            return false; // Ya existe
        }

        String hash = sha256(password);
        Usuario usuario = new Usuario(nombre, email, hash);
        usuarioRepository.save(usuario);
        return true;
    }

    // Método para login (devuelve Usuario o null)
    public Usuario login(String email, String password) {
        String hash = sha256(password);
        Optional<Usuario> userOpt = usuarioRepository.findByEmailAndPasswordHash(email, hash);
        return userOpt.orElse(null);
    }

    // Método para hash SHA-256
    private String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
