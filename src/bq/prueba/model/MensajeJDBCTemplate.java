package bq.prueba.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import bq.prueba.model.Mensaje;
import bq.prueba.model.DAO.MensajeDAO;
import bq.prueba.model.mapper.MensajeMapper;

public class MensajeJDBCTemplate implements MensajeDAO {
	
	@Autowired
	private DataSource dataSource;

	public Mensaje getMensaje(Integer id) {
		String SQL = "select * from Mensaje where id = ?";

		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		Mensaje Mensaje = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new MensajeMapper());
		return Mensaje;
	}

	public List<Mensaje> listMensajes() {
		String SQL = "select * from Mensaje";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		List <Mensaje> Mensajes = jdbcTemplateObject.query(SQL, 
				new MensajeMapper());
		return Mensajes;
	}

	public void delete(Integer id){
		String SQL = "delete from Mensaje where id = ?";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id );
		return;
	}

	public void update(Integer id,String text) {
		String SQL = "update Mensaje set text = ? where id = ?";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		jdbcTemplateObject.update(SQL, text, id);
		System.out.println("Updated Record with ID = " + id );
		return;
		
	}

	public void create(String author, String text) {
		String SQL = "insert into Mensaje (author, text) values (?, ?)";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		jdbcTemplateObject.update( SQL, author, text);
		System.out.println("Created Record Author = " + author + " Text = " + text );
		return;
		
	}

	public List<Mensaje> mensajesDe(String author) {
		String SQL = "select * from Mensaje where author = ?";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		List <Mensaje> Mensajes = jdbcTemplateObject.query(SQL, 
				new MensajeMapper(), author);
		return Mensajes;
	}

	@Override
	public List<String> listAutores() {
		String SQL = "select author from Mensaje";
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);  
		List<String> resultSetList = jdbcTemplateObject.queryForList(SQL, String.class);
		return resultSetList;
	}

	@Override
	public void saveDBtoCSV(String file) {
		try {
			Connection con = dataSource.getConnection();
			Statement st = con.createStatement();
			String query = "SELECT id,author,text into OUTFILE '"+file+ "' FIELDS TERMINATED BY ',' FROM Mensaje";
            st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;	
	}
}
