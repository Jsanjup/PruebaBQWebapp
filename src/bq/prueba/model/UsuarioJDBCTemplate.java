package bq.prueba.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import bq.prueba.model.Usuario;
import bq.prueba.model.DAO.UsuarioDAO;
import bq.prueba.model.mapper.UsuarioMapper;

public class UsuarioJDBCTemplate implements UsuarioDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public Usuario getUsuario(Integer id) {
		String SQL = "select * from Usuario where id = ?";
		Usuario Usuario = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new UsuarioMapper());
		return Usuario;
	}

	public List<Usuario> listUsuarios() {
		String SQL = "select * from Usuario";
		List <Usuario> Usuarios = jdbcTemplateObject.query(SQL, 
				new UsuarioMapper());
		return Usuarios;
	}

	public void delete(Integer id){
		String SQL = "delete from Usuario where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id );
		return;
	}


	@Override
	public void create(String name, String email, String nick) {
		String SQL = "insert into Usuario (name, email, nick) values (?, ?, ?)";
		jdbcTemplateObject.update( SQL, name, email, nick);
		System.out.println("Created User Name = " + name + " Email = " + email + " Nick = "+ nick);
		return;
		
	}

	public void update(Integer id, String name, String email, String nick) {
		String SQL = "update Usuario set name = ?, email = ?, nick =?  where id = ?";
		jdbcTemplateObject.update(SQL, name, email, nick);
		System.out.println("Updated Record with ID = " + id );
		return;
		
	}
}
