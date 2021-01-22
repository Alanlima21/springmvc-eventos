package com.alanlima.eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alanlima.eventos.entities.Evento;
import com.alanlima.eventos.services.EventoService;

@Controller
public class EventoController {

	@Autowired
	private EventoService service;
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String findAll() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String insert(Evento obj) {
		obj = service.insert(obj);
		return "redirect:/cadastrarEvento";
	}
}
