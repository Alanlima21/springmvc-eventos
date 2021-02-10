package com.alanlima.eventos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("evento/formEvento");
		return mv;
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public ModelAndView insert(@Valid Evento obj, BindingResult result,
			RedirectAttributes atributos) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("redirect:/cadastrarEvento");
			List<String> msg = new ArrayList<>();
			for(ObjectError erro : result.getAllErrors()) {
				msg.add(erro.getDefaultMessage());
			}
			for(String ms : msg) {
				atributos.addFlashAttribute("mensagem", ms);
			}
			return mv;
		}
		obj = service.insert(obj);
		ModelAndView mv = new ModelAndView("redirect:/cadastrarEvento");
		atributos.addFlashAttribute("mensagem", "Evento salvo com sucesso");
		return mv;
	}

	@RequestMapping(value = "/eventos", method = RequestMethod.GET)
	public ModelAndView findAllEventos() {
		ModelAndView mv = new ModelAndView("index");
		List<Evento> list = service.findAll();
		mv.addObject("eventos", list);
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView findById(@PathVariable Integer id) {
		Evento obj = service.findById(id);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", obj);
		List<Convidado> list = convidadoService.findAllByEvento(id);
		mv.addObject("convidados", list);
		return mv;
	}
	
	@RequestMapping("/deleteEvento")
	public ModelAndView delete(Integer id) {
		service.delete(id);
		ModelAndView mv = new ModelAndView("redirect:/eventos");
		return mv;
	}
	
	@RequestMapping("/deleteConvidado")
	public ModelAndView deleteConvidado(Integer cod) {
		Convidado obj = convidadoService.findById(cod);
		convidadoService.delete(cod);
		Evento evento = obj.getEvento();
		Integer id = evento.getId();
		ModelAndView mv = new ModelAndView("redirect:/"+id);
		return mv;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ModelAndView insert(@PathVariable Integer id, @Valid Convidado convidado, BindingResult result,
			RedirectAttributes atributos) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("redirect:/{id}");
			List<String> msg = new ArrayList<>();
			for(ObjectError erro : result.getAllErrors()) {
				msg.add(erro.getDefaultMessage());
			}
			for(String ms : msg) {
				atributos.addFlashAttribute("mensagem", ms);
			}
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/{id}");
		convidadoService.insert(convidado, id);
		atributos.addFlashAttribute("mensagem", "Convidado salvo com sucesso");
		return mv;
	}
		
	/*@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ModelAndView insert(@PathVariable Integer id, @Valid Convidado convidado, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("redirect:/{id}");
			List<String> msg = new ArrayList<>();
			for(ObjectError erro : result.getAllErrors()) {
				msg.add(erro.getDefaultMessage());
			}
			mv.addObject("msg", msg);
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/{id}");
		convidadoService.insert(convidado, id);
		return mv;
	}*/
}
