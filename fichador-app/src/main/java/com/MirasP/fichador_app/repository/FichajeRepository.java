package com.MirasP.fichador_app.repository;

import com.MirasP.fichador_app.entity.Fichaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichajeRepository extends JpaRepository<Fichaje, Integer> {
}
