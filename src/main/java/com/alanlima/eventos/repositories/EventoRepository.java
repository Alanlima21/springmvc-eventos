package com.alanlima.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alanlima.eventos.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
