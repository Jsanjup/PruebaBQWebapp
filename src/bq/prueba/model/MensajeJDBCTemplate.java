package bq.prueba.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import bq.prueba.model.Mensaje;
import bq.prueba.model.DAO.MensajeDAO;
import bq.prueba.model.mapper.MensajeMapper;

public class MensajeJDBCTemplate implements MensajeDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public Mensaje getMensaje(Integer id) {
		String SQL = "select * from Mensaje where id = ?";
		Mensaje Mensaje = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new MensajeMapper());
		return Mensaje;
	}

	public List<Mensaje> listMensajes() {
		String SQL = "select * from Mensaje";
		List <Mensaje> Mensajes = jdbcTemplateObject.query(SQL, 
				new MensajeMapper());
		return Mensajes;
	}

	public void delete(Integer id){
		String SQL = "delete from Mensaje where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id );
		return;
	}

	public void update(Integer id,String text) {
		String SQL = "update Mensaje set text = ? where id = ?";
		jdbcTemplateObject.update(SQL, text, id);
		System.out.println("Updated Record with ID = " + id );
		return;
		
	}

	public void create(String author, String text, Integer authorId) {
		String SQL = "insert into Mensaje (author, authorid, text) values (?, ?, ?)";
		jdbcTemplateObject.update( SQL, author, authorId, text);
		System.out.println("Created Record Author = " + author + " Text = " + text );
		return;
		
	}

	public List<Mensaje> mensajesDe(Integer authorId) {
		String SQL = "select * from Mensaje where authorid = ?";
		List <Mensaje> Mensajes = jdbcTemplateObject.query(SQL, 
				new MensajeMapper(), authorId);
		return Mensajes;
	}
}
