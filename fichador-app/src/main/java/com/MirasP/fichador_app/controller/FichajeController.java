package com.MirasP.fichador_app.controller;

import com.MirasP.fichador_app.service.FichajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fichajes")
public class FichajeController {

    private final FichajeService fichajeService;

    public FichajeController(FichajeService fichajeService) {
        this.fichajeService = fichajeService;
    }

    @PostMapping("/fichar")
    public ResponseEntity<String> fichar(
            @RequestParam String email,
            @RequestParam String tipo
    ) {
        boolean registrado = fichajeService.fichar(email, tipo);
        if (registrado) {
            return ResponseEntity.ok("Fichaje registrado");
        } else {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
    }
}
