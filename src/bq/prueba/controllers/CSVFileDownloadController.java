package bq.prueba.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import bq.prueba.model.Mensaje;
import bq.prueba.model.MensajeJDBCTemplate;
 
/**
 * This Spring controller class implements a CSV file download functionality.
 *
 */

@Controller
public class CSVFileDownloadController {
	
	@Autowired
	MensajeJDBCTemplate mensajeDao;
	
    @RequestMapping(value = "/csvExport", method = RequestMethod.GET)
    public void downloadCSV(Model model, HttpServletResponse response) throws IOException {
 
        String csvFileName = "mensajes.csv";
 
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        List<Mensaje> mensajes = mensajeDao.listMensajes();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
 
        String[] header = {"Author","Text"};
 
        csvWriter.writeHeader(header);
 
        for (Mensaje mensaje : mensajes) {
            csvWriter.write(mensaje, header);
        }
 
        csvWriter.close();
    }
}