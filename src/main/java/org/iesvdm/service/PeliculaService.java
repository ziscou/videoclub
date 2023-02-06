package org.iesvdm.service;

import java.util.List;
import java.util.Set;

import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;
import org.iesvdm.domain.Pelicula;
import org.iesvdm.dto.PeliculaDTO;
import org.iesvdm.repository.CategoriaRepository;
import org.iesvdm.repository.IdiomaRepository;
import org.iesvdm.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {

	@Value("${app.videoclub_examen.array.clasificaciones}")
	private String[] arrayClasificaciones;
	@Value("#{${app.videoclub_examen.set.clasificaciones}}")
	private Set<String> setCaracteristicasEspeciales;
	
	private PeliculaRepository peliculaRepository;
	private IdiomaRepository idiomaRepository;
	private CategoriaRepository categoriaRepository;
	
	public PeliculaService(PeliculaRepository peliculaRepository, IdiomaRepository idiomaRepository, CategoriaRepository categoriaRepository) {
		this.peliculaRepository = peliculaRepository;
		this.idiomaRepository = idiomaRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
	public List<Pelicula> all() {
		return this.peliculaRepository.findAll();
	}
	
	public List<PeliculaDTO> allDTO() {
		return this.peliculaRepository.findAllDTO();
	}

	public Pelicula create(Pelicula pelicula) {
		return this.peliculaRepository.create(pelicula);
	}
	
	public List<Categoria> getListaCategorias() {
		return this.categoriaRepository.findAll();
	}
	
	public List<Idioma> getListaIdiomas() {
		return this.idiomaRepository.findAll();
	}
	
	public String[] getArrayClasificaciones() {
		return arrayClasificaciones;
	}

	public Set<String> getSetCaracteristicasEspeciales() {
		return setCaracteristicasEspeciales;
	}
	
}
