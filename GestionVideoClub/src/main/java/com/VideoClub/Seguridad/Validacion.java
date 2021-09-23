package com.VideoClub.Seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.VideoClub.DAO.UsuarioDAO;
import com.VideoClub.negocio.Usuario;

@Service
public class Validacion implements UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional <Usuario> usuario = usuarioDAO.findByNombre(nombre);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		else {
		throw new UsernameNotFoundException("Usuario desconocido");
		}
		
	}

}
