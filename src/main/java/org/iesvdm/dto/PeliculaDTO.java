package org.iesvdm.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.iesvdm.domain.Categoria;
import org.iesvdm.domain.Idioma;
import org.iesvdm.domain.Pelicula;

import lombok.Data;

@Data
public class PeliculaDTO extends Pelicula {
	
	private Idioma idioma;
	private Idioma idiomaOriginal;
	private Categoria categoria;
	

	public PeliculaDTO(long idPelicula, String titulo, String descripcion, Date anyoLanzamiento, long idIdioma,
			long idIdiomaOriginal, int duracionAlquiler, BigDecimal rentalRate, int duracion,
			BigDecimal replacementCost, String clasificacion, String caracteristicasEspeciales,
			Date ultimaActualizacion, Idioma idioma, Idioma idiomaOriginal, Categoria categoria) {
		super(idPelicula, titulo, descripcion, anyoLanzamiento, idIdioma, idIdiomaOriginal, duracionAlquiler, rentalRate,
				duracion, replacementCost, clasificacion, caracteristicasEspeciales, ultimaActualizacion);
		this.categoria = categoria;
		this.idioma = idioma;
		this.idiomaOriginal = idiomaOriginal;
	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeliculaDTO other = (PeliculaDTO) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(idioma, other.idioma)
				&& Objects.equals(idiomaOriginal, other.idiomaOriginal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(categoria, idioma, idiomaOriginal);
		return result;
	}

	
	
}
