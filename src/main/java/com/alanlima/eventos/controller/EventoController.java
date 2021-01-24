package com.alanlima.eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alanlima.eventos.entities.Convidado;
import com.alanlima.eventos.entities.Evento;
import com.alanlima.eventos.services.ConvidadoService;
import com.alanlima.eventos.services.EventoService;

@Controller
public class EventoController {

	@Autowired
	private EventoService service;
	@Autowired
	private ConvidadoService convidadoService;
	
	@RequestMapping(value = "/cadastrarEvento")
	public String findAll() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String insert(Evento obj) {
		obj = service.insert(obj);
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping(value = "/eventos", method = RequestMethod.GET)
	public ModelAndView findAllEventos(){
		ModelAndView mv = new ModelAndView("index");
		List<Evento> list = service.findAll();
		mv.addObject("eventos", list);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ModelAndView findById(@PathVariable Integer id) {
		Evento obj = service.findById(id);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", obj);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public String insert(@PathVariable Integer id , Convidado convidado) {
		convidadoService.insert(convidado, id);
		return "redirect:/{id}";
	}
}
