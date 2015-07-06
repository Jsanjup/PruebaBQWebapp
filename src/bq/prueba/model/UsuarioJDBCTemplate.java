package bq.prueba.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bq.prueba.model.Usuario;
import bq.prueba.model.DAO.UsuarioDAO;
import bq.prueba.model.mapper.UsuarioMapper;

public class UsuarioJDBCTemplate implements UsuarioDAO {
	
	@Autowired
	private DataSource dataSource;
	
	public Usuario getUsuario(Integer id) {
		String SQL = "select * from Usuario where id = ?";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		Usuario Usuario = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new UsuarioMapper());
		return Usuario;
	}

	@Override
	public Usuario getUsuario(String nick) {
		String SQL = "select * from Usuario where nick = ?";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		Usuario Usuario = jdbcTemplateObject.queryForObject(SQL, new Object[]{nick}, new UsuarioMapper());
		return Usuario;
	}
	
	@Override
	public List<Usuario> listUsuarios() {
		String SQL = "select * from Usuario";

		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		List <Usuario> Usuarios = jdbcTemplateObject.query(SQL, 
				new UsuarioMapper());
		return Usuarios;
	}

	@Override
	public void delete(Integer id){
		String SQL = "delete from Usuario where id = ?";

		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id );
		return;
	}


	@Override
	public void create(String name, String email, String nick) {
		if (dataSource == null){
			return;
		}
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		String SQL = "insert into Usuario (name, email, nick) values (?, ?, ?)";
		if (jdbcTemplateObject == null) return;
		jdbcTemplateObject.update(SQL, new Object[]{name, email, nick});
		System.out.println("Created User Name = " + name + " Email = " + email + " Nick = "+ nick);
		return;
		
	}

	@Override
	public void update(Integer id, String name, String email, String nick) {
		String SQL = "update Usuario set name = ?, email = ?, nick =?  where id = ?";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		jdbcTemplateObject.update(SQL, name, email, nick);
		System.out.println("Updated Record with ID = " + id );
		return;
		
	}
}
