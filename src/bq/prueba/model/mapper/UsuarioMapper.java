package bq.prueba.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bq.prueba.model.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {
	
   public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
      Usuario Usuario = new Usuario();
      Usuario.setId(rs.getInt("id"));
      Usuario.setName(rs.getString("name"));
      Usuario.setEmail(rs.getString("email"));
      Usuario.setNick(rs.getString("nick"));
      return Usuario;
   }
}
