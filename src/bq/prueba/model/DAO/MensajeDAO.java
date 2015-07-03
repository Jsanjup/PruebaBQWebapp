package bq.prueba.model.DAO;

import java.util.List;

import javax.sql.DataSource;

import bq.prueba.model.Mensaje;

public interface MensajeDAO {
	
	public void setDataSource(DataSource ds);
	   
	   public void create(String author, String text, Integer authorId);
	   
	   public Mensaje getMensaje(Integer id);
	   
	   public List<Mensaje> listMensajes();
	   
	   public List<Mensaje> mensajesDe (Integer authorId);
	   
	   public void delete(Integer id);
	   
	   public void update(Integer id, String text);

}
