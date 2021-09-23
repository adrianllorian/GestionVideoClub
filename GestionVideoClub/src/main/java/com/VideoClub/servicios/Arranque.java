package com.VideoClub.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.VideoClub.DAO.UsuarioDAO;
import com.VideoClub.negocio.Usuario;

@Component
public class Arranque {

	@Autowired
	private UsuarioDAO usuarioADMIN;
	
	@EventListener(ContextRefreshedEvent.class)
    void contextRefreshedEvent() {
		usuarioADMIN.save(new Usuario("Anon","Q",123));
	}
}
