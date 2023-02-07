package org.iesvdm.repository;

import java.util.List;
import java.util.Map;

import org.iesvdm.domain.Categoria;
import org.iesvdm.dto.AlmacenDTO;
import org.iesvdm.dto.CategoriaDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository{

	private JdbcTemplate jdbcTemplate;
	
	public CategoriaRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override	
	public Categoria find(Long id) {
		
		Categoria categoria = this.jdbcTemplate.queryForObject("select * from categoria where id_categoria = ?"
										, (rs, rowNum) -> new Categoria(rs.getInt("id_categoria")
																		, rs.getString("nombre")
																		, rs.getDate("ultima_actualizacion"))
										, id);
		
		return categoria;
	}

	@Override
	public CategoriaDTO findDTO(Long id) {
		
		CategoriaDTO categoriaDTO = this.jdbcTemplate.queryForObject("""
					select C.*, count(P.id_pelicula) as conteoPelisCat from categoria C left join pelicula_categoria P_C on C.id_categoria = P_C.id_categoria 
					left join pelicula P on P_C.id_pelicula = P.id_pelicula where C.id_categoria = ? group by C.id_categoria 	
				"""
										, (rs, rowNum) -> new CategoriaDTO(rs.getInt("id_categoria")
																		, rs.getString("nombre")
																		, rs.getDate("ultima_actualizacion")
																		, rs.getInt("conteoPelisCat"))
										, id);
		
		return categoriaDTO;
	}

	@Override
	public List<Categoria> findAll() {
		
		List<Categoria> listaCategoria = this.jdbcTemplate.query("select * from categoria", (rs, rowNum) -> new Categoria(rs.getInt("id_categoria")
																														, rs.getString("nombre")
																														, rs.getDate("ultima_actualizacion")));

		return listaCategoria;
	}

	@Override
	public List<AlmacenDTO> findPelAlm(Long id) {
		//
		List<AlmacenDTO> lista = this.jdbcTemplate.query("""
SELECT 
    i.id_almacen AS id, COUNT(p.id_pelicula) AS peliculas
FROM
    categoria c
        LEFT OUTER JOIN
    pelicula_categoria pc ON c.id_categoria = pc.id_categoria
        LEFT OUTER JOIN
    pelicula p ON pc.id_pelicula = p.id_pelicula
        LEFT OUTER JOIN
    inventario i ON i.id_pelicula = p.id_pelicula
WHERE
    c.id_categoria = ?
GROUP BY c.id_categoria , i.id_almacen;
				"""
										, (rs, rowNum) -> new AlmacenDTO(
												rs.getInt("id"),
												rs.getLong("peliculas"))
										, id);
		
		return lista;
	}
	
}
