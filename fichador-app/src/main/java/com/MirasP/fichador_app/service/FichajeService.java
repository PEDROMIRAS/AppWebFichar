package com.MirasP.fichador_app.service;

import com.MirasP.fichador_app.entity.Fichaje;
import com.MirasP.fichador_app.entity.Usuario;
import com.MirasP.fichador_app.repository.FichajeRepository;
import com.MirasP.fichador_app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FichajeService {

    private final FichajeRepository fichajeRepository;
    private final UsuarioRepository usuarioRepository;

    public FichajeService(FichajeRepository fichajeRepository, UsuarioRepository usuarioRepository) {
        this.fichajeRepository = fichajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean fichar(String email, String tipo) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) {
            return false;
        }
        Fichaje fichaje = new Fichaje(usuario, tipo, LocalDateTime.now());
        fichajeRepository.save(fichaje);
        return true;
    }
}
