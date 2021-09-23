package com.VideoClub.Rutas;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.VideoClub.DAO.JuegoDAO;
import com.VideoClub.DAO.UsuarioDAO;
import com.VideoClub.negocio.Juego;
import com.VideoClub.negocio.Usuario;

@Controller
public class RutasWeb {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private JuegoDAO juegoDAO;
	
	@GetMapping("/")
	public String index() {
		String salida ="index";
		return salida;
	}
	
	@GetMapping("/verUsuarios")
	public ModelAndView verUsusarios() {
		ModelAndView model = new ModelAndView();
		List <Usuario> listaUsuarios = (List<Usuario>) usuarioDAO.findAll();
		model.addObject("listaUsuarios",listaUsuarios);
		model.setViewName("VerUsuarios");
		return model;
	}
	
	
	@GetMapping("/verJuegos")
	public ModelAndView verJuegos() {
		ModelAndView model = new ModelAndView();
		List <Juego> listaJuegos = (List<Juego>) juegoDAO.findAll();
		model.addObject("listaJuegos", listaJuegos);
		model.setViewName("VerJuegos");
		return model;
	}
	
	@GetMapping("/agregarUsuario")
	public ModelAndView agregarUsuario() {
		ModelAndView model = new ModelAndView();
		model.addObject("usuario", new Usuario());
		model.setViewName("AgregarUsuario");
		return model;
	}
	
	@GetMapping("/agregarJuego")
	public ModelAndView agregarJuego() {
		ModelAndView model = new ModelAndView();
		model.addObject("juego", new Juego());
		model.setViewName("AgregarJuego");
		return model;
	}

	@PostMapping("/postAgregarUsuario")
	public String postAgregarUsuario(@ModelAttribute Usuario usuario) {
		usuarioDAO.save(usuario);
		return "redirect:/";
	}
	
	@PostMapping("/postAgregarJuego")
	public ModelAndView postAgregarJuego(@ModelAttribute @Valid Juego juego, Errors errores) {
		ModelAndView model = new ModelAndView();
		if(errores.hasErrors()) {
			model.setViewName("AgregarJuego");
			model.addObject("errors",errores.getAllErrors());
		}
		else {
			
			juegoDAO.save(juego);
			model.setViewName("redirect:/verJuegos");
		}
		
		return model;
	}

	
	@GetMapping("/borrarUsuario/{id}")
	public ModelAndView borrarUsuario(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Optional <Usuario> usuario = usuarioDAO.findById(id);
		if(usuario.isPresent()) {
			usuarioDAO.deleteById(id);
			model.setViewName("redirect:/verUsuarios");
		}
		else {
			model.setViewName("errorUsuarioNoEncontrado");
		}
		return model;
	}
	
	@GetMapping("/editarUsuario/{id}")
	public ModelAndView editarUsuario(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Usuario usuario = usuarioDAO.findById(id).get();
		model.addObject("usuario", usuario);
		model.setViewName("AgregarUsuario");
		return model;
	}
	
	@GetMapping("/borrarJuego/{id}")
	public ModelAndView borrarJuego(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Optional <Juego> juego = juegoDAO.findById(id);
		if(juego.isPresent()) {
			juegoDAO.deleteById(id);
			model.setViewName("redirect:/verJuegos");
		}
		else {
			model.setViewName("errorJuegoNoEncontrado");
		}
		return model;
	}
	
	@GetMapping("/editarJuego/{id}")
	public ModelAndView editarJuego(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Juego juego = juegoDAO.findById(id).get();
		model.addObject("juego", juego);
		model.setViewName("AgregarJuego");
		return model;
	}
	
	
	@GetMapping("/login")
	public String seguridad(HttpSession sesion) {
		
		//sesion.setAttribute("carrito", new Carrito());
		
		return "start";
	}	
	

	@GetMapping("/logout")
	public String finalizar(Authentication authentication) {
		
		return "ok";
	}
}
