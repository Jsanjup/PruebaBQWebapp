package bq.prueba.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bq.prueba.model.Mensaje;

public class MensajeMapper implements RowMapper<Mensaje> {
	
   public Mensaje mapRow(ResultSet rs, int rowNum) throws SQLException {
      Mensaje Mensaje = new Mensaje();
      Mensaje.setId(rs.getInt("id"));
      Mensaje.setAuthor(rs.getString("author"));
      Mensaje.setText(rs.getString("text"));
      return Mensaje;
   }
}
