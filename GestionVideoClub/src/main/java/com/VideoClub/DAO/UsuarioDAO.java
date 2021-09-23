package com.VideoClub.DAO;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VideoClub.negocio.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository <Usuario,Integer>{
	
	Optional <Usuario> findByNombre(String nombre);

	

}
