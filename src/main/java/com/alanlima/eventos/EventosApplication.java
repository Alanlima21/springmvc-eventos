package com.alanlima.eventos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alanlima.eventos.entities.Evento;
import com.alanlima.eventos.repositories.EventoRepository;

@SpringBootApplication
public class EventosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EventosApplication.class, args);
	}

	@Autowired
	private EventoRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Evento ev = new Evento(null, "Teste", "Teste", "27/01/2021", "22");
		repo.save(ev);
	}

}
