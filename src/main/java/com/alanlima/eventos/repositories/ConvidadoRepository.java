package com.alanlima.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alanlima.eventos.entities.Convidado;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Integer> {

}
