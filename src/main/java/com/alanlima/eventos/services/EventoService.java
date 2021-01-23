package com.alanlima.eventos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlima.eventos.entities.Evento;
import com.alanlima.eventos.repositories.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repo;
	
	@Transactional
	public Evento insert(Evento obj) {
		Evento evento = new Evento(null, obj.getNome(), obj.getLocal(), obj.getData(), obj.getHorario());
		evento = repo.save(evento);
		return evento;
	}
	
	public List<Evento> findAll(){
		List<Evento> list = repo.findAll();
		return list;
	}
	
	public Evento findById(Integer id) {
		Evento obj = repo.findById(id).orElse(null);
		return obj;
	}
}
