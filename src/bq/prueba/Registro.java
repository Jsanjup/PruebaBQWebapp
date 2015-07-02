package bq.prueba;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/registro")
public class Registro{
 
   @RequestMapping(method = RequestMethod.GET)
   public String printForm(ModelMap model) {
      model.addAttribute("title", "Nuevo usuario");
      return "registro";
   }
   
   @RequestMapping(method = RequestMethod.POST)
   public String addUsuario(ModelMap model, @RequestParam(value="name") String name, @RequestParam(value="email") String email, @RequestParam(value="nick") String nick){
	   Usuario nuevo = new Usuario (name, email, nick);
	   MainApp.addUsuario(nuevo);
	   MainApp.alert_msg = "Usuario "+nick+" creado con éxito.";
	   return "redirect:index";
   }

}