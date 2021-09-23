package com.VideoClub.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VideoClub.negocio.Juego;

@Repository
public interface JuegoDAO extends CrudRepository <Juego, Integer> {

}
