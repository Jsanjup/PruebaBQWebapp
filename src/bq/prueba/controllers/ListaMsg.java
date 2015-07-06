package bq.prueba.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.ui.ModelMap;

import bq.prueba.model.Mensaje;
import bq.prueba.model.MensajeJDBCTemplate;

@Controller
@RequestMapping("/lista_msg")
public class ListaMsg{
	
	@Autowired
	MensajeJDBCTemplate mensajeDao;
 
   @RequestMapping(method = RequestMethod.GET)
   public String historial(ModelMap model) {
	   model.addAttribute("title", "Todos los mensajes");
	   List<Mensaje> mensajes = mensajeDao.listMensajes();
	   List<String> autores = mensajeDao.listAutores();
	   model.addAttribute("authorSelected", "Seleccione un usuario");
	   model.addAttribute("mensajes", mensajes);
	   model.addAttribute("autores", autores);
      return "lista_msg";
   }
   
   @RequestMapping(method = RequestMethod.POST)
   public String filtrarAutor(ModelMap model,@RequestParam(value="authorlist") String author ) {
	   if (!author.equals("none") && ! author.equals("all")){
	   model.addAttribute("title", "Mensajes de " + author);
	   List<Mensaje> mensajes = mensajeDao.mensajesDe(author);
	   List<String> autores = mensajeDao.listAutores();
	   model.addAttribute("authorSelected", author);
	   model.addAttribute("mensajes", mensajes);
	   model.addAttribute("autores", autores);
      return "lista_msg";
	   } else return historial(model);
   }

}