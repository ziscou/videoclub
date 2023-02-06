package org.iesvdm.repository;

import java.util.List;

import org.iesvdm.domain.Idioma;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IdiomaRepositoryImpl implements IdiomaRepository {

	private JdbcTemplate jdbcTemplate;

	public IdiomaRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<Idioma> findAll() {
		List<Idioma> listIdioma =  this.jdbcTemplate.query("select * from idioma", (rs, rowNum) -> new Idioma(rs.getLong("id_idioma")
																					, rs.getString("nombre")
																					, rs.getDate("ultima_actualizacion")));
		return listIdioma;
	}


}
