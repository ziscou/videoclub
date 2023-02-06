package org.iesvdm.repository;

import java.util.List;

import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;

public interface PeliculaRepository {

	public List<Pelicula> findAll();
	
	public List<PeliculaDTO> findAllDTO();
	
	public Pelicula create(Pelicula pelicula);

}
