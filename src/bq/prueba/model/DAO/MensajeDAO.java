package bq.prueba.model.DAO;

import java.util.List;

import javax.sql.DataSource;

import bq.prueba.model.Mensaje;

public interface MensajeDAO {
	   
	   public void create(String author, String text);
	   
	   public Mensaje getMensaje(Integer id);
	   
	   public List<Mensaje> listMensajes();
	   
	   public List<String> listAutores();
	   
	   public List<Mensaje> mensajesDe (String author);
	   
	   public void delete(Integer id);
	   
	   public void update(Integer id, String text);
	   
	   public void saveDBtoCSV (String file);

}
