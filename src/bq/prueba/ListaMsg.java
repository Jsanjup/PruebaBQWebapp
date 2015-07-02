package bq.prueba;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/lista_msg")
public class ListaMsg{
 
   @RequestMapping(method = RequestMethod.GET)
   public String historial(ModelMap model) {
	   model.addAttribute("title", "Todos los mensajes");
	   model.addAttribute("mensajes", MainApp.getMensajes());
      return "lista_msg";
   }

}