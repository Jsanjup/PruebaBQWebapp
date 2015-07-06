package bq.prueba.model.DAO;

import java.util.List;

import javax.sql.DataSource;

import bq.prueba.model.Usuario;

public interface UsuarioDAO {
	   public void create(String name, String email, String nick);
	   
	   public Usuario getUsuario(Integer id);
	   
	   public Usuario getUsuario(String nick);
	   
	   public List<Usuario> listUsuarios();
	   
	   public void delete(Integer id);
	   
	   public void update(Integer id, String name, String email, String nick);
}
