package com.MirasP.fichador_app.controller;

import com.MirasP.fichador_app.entity.Usuario;
import com.MirasP.fichador_app.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar nuevo usuario (POST)
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestParam String nombre, @RequestParam String email, @RequestParam String password) {
        boolean creado = usuarioService.registrar(nombre, email, password);
        if (creado) {
            return ResponseEntity.ok("Usuario registrado correctamente");
        } else {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }
    }

    // Login (POST)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Usuario user = usuarioService.login(email, password);
        if (user != null) {
            // No se devuelve la contraseña, solo datos básicos
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Email o contraseña incorrectos");
        }
    }
}
