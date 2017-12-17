package web_semantica.app.webservices;

import java.io.FileNotFoundException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import web_semantica.app.Backend;
import web_semantica.app.Evento;

@Path("/")
public class WebService {
	@Path("/")
	@GET
	@Produces("application/json")
	public Response index() throws FileNotFoundException{
		List <String>list = Backend.listaColegios("colegios-publicos-csv-updated.ttl");
		String html = "<html>"+
		"<head>"
		+ "<meta charset=\"utf-8\">    "
		+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">    "
		+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">    "
		+ "<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->"
		+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\"> "		
		+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\"> "		
		+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>"						
		+ "<style> "
		+ "        .jumbotron {position: relative; overflow: hidden; color:#fff;}"
		+ "        .jumbotron h2 { position: relative; z-index: 2; 	margin-left: 170px;} "
		+ "        .jumbotron p { position: relative; z-index: 2; 	margin-left: 170px;} "
		+ "        .jumbotron img {position: absolute;left: 0;top: 0;width: 100%; opacity: 0.3;} "
		+ "</style> "		
		+ "<title> Buscador de eventos</title> " 		
		+ "</head>" 
		+ "<body>" 		 	
		+ " <div class=\"jumbotron\"> "
		+ "   <h2>BUSCADOR DE EVENTOS DEL AYUNTAMIENTO DE MADRID</h2> "
		+ "   <p>Seleccione su centro escolar y el buscador le mostrará los diferentes eventos de su distrito</p>"
		+ "   <img src=\"http://circuitonacionaldepoker.es/wp-content/uploads/2017/11/madrid.jpg\"> "
		+ " </div>"		
		+ " <div class=\"container\">"	
		+ " <form action=\"http://localhost:8080/event-app/search\" method=\"get\">"		
		+ "   <div class=\"row\" style=\"width:97.5%;\">"
		+ "		<div class=\"col-sm-6 col-md-12\">"	 		
		+ "        <div class=\"form-group\">" 
		+ "           <label for=\"inputTitle\">Seleccione su centro escolar:</label>"
		+ "<div>"
		+ "<select name=\"school\">\r\n" + 
		"  <option value=\"none\">-Seleccionar centro escolar-</option>\r\n" ;
		for (String colegio:list) {
			html = html +"  <option value=\""+colegio+"\">"+colegio+"</option>\r\n";
		}
		html = html + "   </select> </div>     </div> "
		+ "     </div> "
		+ "   </div> ";
		html = html
				+ " <div class=\"row\" style=\"width:97.5%;\">"
				+ "		   <div class=\"col-sm-6 col-md-12\">"
				+ " <input type=\"submit\" class=\"btn btn-default\" value=\"Buscar\" />" 
				+ "</form>" 
				+ "     </div><!-- /.col-lg-6 --> "
				+ "  </div><!-- /.row --> "
				+ "  </div><!-- /.container --> "
				+ "<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> " 
				+ " <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>" 
				+ " <!-- Include all compiled plugins (below), or include individual files as needed -->" 
				+ " <script src=\"js/bootstrap.min.js\"></script>" 
				+ "</body>" 
				+"</html>";

		
		new MediaType();
		MediaType type = MediaType.TEXT_HTML_TYPE;
		return Response.status(200).entity(html).type(type).build();
	}
	@Path("/search")
	@GET
	@Produces("application/json")
	public Response show(@QueryParam("school") String colegio) throws FileNotFoundException{
		String colesRDF = "colegios-publicos-csv-updated.ttl";
		String eventRDF = "agenda-eventos-culturales-csv-updated.ttl";
		
		String distrito = Backend.districtChoice(colesRDF, colegio);
		List <String> list = Backend.EventSeeker(eventRDF,distrito);
		List <Evento> eventos = Backend.EventReader(eventRDF, list);
		String html = "<html>"+
		"<head>"
		+ "<meta charset=\"utf-8\">    "
		+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">    "
		+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">    "
		+ "<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->"
		+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\"> "		
		+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\"> "		
		+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>"						
		+ "<style> "
		+ "        .jumbotron {position: relative; overflow: hidden; color:#fff;}"
		+ "        .jumbotron h2 { position: relative; z-index: 2; 	margin-left: 170px;} "
		+ "        .jumbotron p { position: relative; z-index: 2; 	margin-left: 170px;} "
		+ "        .jumbotron img {position: absolute;left: 0;top: 0;width: 100%; opacity: 0.3;} "
		+ "</style> "		
		+ "<title> Buscador de eventos</title> " 		
		+ "</head>" 
		+ "<body>" 		 	
		+ " <div class=\"jumbotron\"> "
		+ "   <h2>BUSCADOR DE EVENTOS DEL AYUNTAMIENTO DE MADRID</h2> "
		+ "   <p>Seleccione su centro escolar y el buscador le mostrará los diferentes eventos de su distrito</p>"
		+ "   <img src=\"http://circuitonacionaldepoker.es/wp-content/uploads/2017/11/madrid.jpg\"> "
		+ " </div>"
		+ " <div class=\"container\">";
		html = html + 
				"  <table class=\"table\">\r\n" + 
				"    <thead>\r\n" + 
				"      <tr>\r\n" + 
				"        <th>Evento</th>\r\n" + 
				"        <th>Lugar</th>\r\n" + 
				"        <th>Días de la Semana</th>\r\n" + 
				"      </tr>\r\n" + 
				"    </thead>\r\n" + 
				"    <tbody>\r\n" ;
		for (Evento e: eventos) {
				html = html + "      <tr>\r\n" + 
				"        <td>"+e.getTitulo()+"</td>\r\n" + 
				"        <td>"+e.getNombreInstalacion()+"</td>\r\n" + 
				"        <td>"+e.getDiasSemana()+"</td>\r\n" + 
				"      </tr>\r\n"; 
				
		}
		html = html + "    </tbody>\r\n" + 
				"  </table>\r\n" + 
				"</div>";
		html = html +"</ul>\r\n";
		html = html
				+ "  </div><!-- /.container --> "
				+ "<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> " 
				+ " <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>" 
				+ " <!-- Include all compiled plugins (below), or include individual files as needed -->" 
				+ " <script src=\"js/bootstrap.min.js\"></script>" 
				+ "</body>" 
				+"</html>";

		
		new MediaType();
		MediaType type = MediaType.TEXT_HTML_TYPE;
		return Response.status(200).entity(html).type(type).build();
	}
}
