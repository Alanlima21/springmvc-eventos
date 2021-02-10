package com.alanlima.eventos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlima.eventos.entities.Convidado;
import com.alanlima.eventos.entities.Evento;
import com.alanlima.eventos.repositories.ConvidadoRepository;

@Service
public class ConvidadoService {

	@Autowired
	private ConvidadoRepository repo;
	@Autowired
	private EventoService service;

	public List<Convidado> findAllByEvento(Integer id) {
		List<Convidado> list = repo.findByEventoId(id);
		return list;
	}

	@Transactional
	public Convidado insert(Convidado obj, Integer id) {
		Evento evento = service.findById(id);
		Convidado convidado = new Convidado(null, obj.getNome(), obj.getRg(), evento);
		convidado = repo.save(convidado);
		return convidado;
	}

	
	public Convidado findById(Integer id) {
		Convidado obj = repo.findById(id).orElse(null);
		return obj;
	}

	public void delete(Integer id) {
		Convidado obj = findById(id);
		if(obj != null) {
			repo.delete(obj);
		}
	}
}
