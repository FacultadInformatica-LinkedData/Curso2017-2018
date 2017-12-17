package web_semantica.app;

import java.io.FileNotFoundException;
import java.util.List;

public class Prueba {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String colegio = "Colegio Público Adolfo Suárez";
		
		String colesRDF = "colegios-publicos-csv-updated.ttl";
		String eventRDF = "agenda-eventos-culturales-csv-updated.ttl";
		
		String distrito = Backend.districtChoice(colesRDF, colegio);
		List <String> list = Backend.EventSeeker(eventRDF,distrito);
		List <Evento> eventos = Backend.EventReader("agenda-eventos-culturales-csv-updated.ttl", list);
		for (Evento e: eventos) {
			System.out.println(e.toString());
		}
	}

}
