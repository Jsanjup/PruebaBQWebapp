package bq.prueba.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.ui.ModelMap;

import bq.prueba.model.Mensaje;

@Controller
@RequestMapping("/snd_msg")
public class EnviarMsg{
 
   @RequestMapping(method = RequestMethod.GET)
   public ModelAndView student() {
      return new ModelAndView("snd_msg", "command", new Mensaje());
   }
   
   @RequestMapping(method = RequestMethod.POST)
   public String addMensaje(@ModelAttribute("SpringWeb") Mensaje msg, 
   ModelMap model) {
	  MainApp.addMensaje(msg);
	  MainApp.alert_msg = msg.getText();
      return "redirect:index";
   }

}