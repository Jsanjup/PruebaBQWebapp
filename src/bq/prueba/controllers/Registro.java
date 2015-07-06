package bq.prueba.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import bq.prueba.model.Usuario;
import bq.prueba.model.UsuarioJDBCTemplate;

@Controller
@RequestMapping("/registro")
public class Registro{

	private static final Logger logger = Logger.getLogger(Registro.class);
	private static final String srcMail = "bq.prueba.webapp@gmail.com";
	private static final String password = "CuentaTesting";

	@Autowired
	UsuarioJDBCTemplate usuarioDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String printForm(ModelMap model) {
		model.addAttribute("title", "Nuevo usuario");
		return "registro";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addUsuario(ModelMap model, @RequestParam(value="name") String name, @RequestParam(value="email") String email, @RequestParam(value="nick") String nick){

		usuarioDAO.create(name, email, nick);
		Usuario nuevo = new Usuario (name, email, nick);
		MainApp.addUsuario(nuevo);
		MainApp.alert_msg = "Usuario "+nick+" creado con éxito.";
		enviarEmail(email);
		return "redirect:index";
	}

	public void enviarEmail(String destino){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(srcMail, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(srcMail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destino));
			message.setSubject("Confirmación de registro");
			message.setText("Estimado usuario,\n  Confirmamos su registro en la aplicación web para la prueba de BQ");
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			// simply log it and go on...
			logger.error("Email no pudo enviarse: \n"+ e);
		}
	}

}