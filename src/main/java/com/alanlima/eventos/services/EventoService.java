package com.alanlima.eventos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanlima.eventos.entities.Evento;
import com.alanlima.eventos.repositories.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repo;
	
	public Evento insert(Evento obj) {
		Evento evento = new Evento(null, obj.getNome(), obj.getLocal(), obj.getData(), obj.getHorario());
		evento = repo.save(evento);
		return evento;
	}
}
