package bq.prueba.controllers;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import bq.prueba.model.Mensaje;
import bq.prueba.model.Usuario;

@Controller
@RequestMapping("/index")
public class MainApp{

	public static ArrayList<Usuario> users;
	public static ArrayList<Mensaje> mensajes;
	public static String alert_msg;

	public static Logger log = Logger.getLogger(MainApp.class.getName());

	@RequestMapping(method = RequestMethod.GET)
	public String printIndex(ModelMap model) {
		model.addAttribute("title", "Prueba aplicación BQ");
		model.addAttribute("registro", "Registrar nuevo usuario");
		model.addAttribute("enviar_msg", "Publicar un mensaje");
		model.addAttribute("historial", "Ver mensajes publicados");
		model.addAttribute("alert_msg", alert_msg);
		alert_msg = null;
		return "index";
	}

	public static ArrayList<Usuario> getUsuarios(){
		if (users == null) users = new ArrayList<Usuario>();
		return users;
	}

	public static void addUsuario (Usuario u){
		if (users == null) users = new ArrayList<Usuario>();
		users.add(u);
	}

	public static ArrayList<Mensaje> getMensajes() {
		if (mensajes == null) mensajes = new ArrayList<Mensaje>();
		return mensajes;
	}
	
	public static void addMensaje(Mensaje m){
		if (mensajes == null) mensajes = new ArrayList<Mensaje>();
		mensajes.add(m);
	}

	public static void setMensajes(ArrayList<Mensaje> mensajes) {
		MainApp.mensajes = mensajes;
	}
}