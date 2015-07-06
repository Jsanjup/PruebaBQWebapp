package bq.prueba.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.ui.ModelMap;

import bq.prueba.model.Mensaje;
import bq.prueba.model.MensajeJDBCTemplate;
import bq.prueba.model.UsuarioJDBCTemplate;

@Controller

public class EnviarMsg{
	
	@Autowired
	UsuarioJDBCTemplate usuarioDao;
	
	@Autowired
	MensajeJDBCTemplate mensajeDao;
 
   @RequestMapping(value="/snd_msg" , method = RequestMethod.GET)
   public ModelAndView student() {
      return new ModelAndView("snd_msg", "command", new Mensaje());
   }
   
   @RequestMapping(value="/addMessage",method = RequestMethod.POST)
   public String addMensaje(@ModelAttribute("SpringWeb") Mensaje msg, 
   ModelMap model) {
	   try{
	  usuarioDao.getUsuario(msg.getAuthor());
	  mensajeDao.create(msg.getAuthor(), msg.getText());
	  MainApp.addMensaje(msg);
	  MainApp.alert_msg = msg.getText() + " añadido con éxito";
	  }
	  catch (EmptyResultDataAccessException e) {
		  MainApp.alert_msg = "Error, el usuario " + msg.getAuthor() + " no puede publicar porque no se encuentra en la base de datos";
	  }
      return "redirect:index";
   }

}