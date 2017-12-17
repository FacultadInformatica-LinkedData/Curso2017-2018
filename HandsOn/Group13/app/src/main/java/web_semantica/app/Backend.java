package web_semantica.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class Backend {
	public static List <String> listaColegios(String rdf) throws FileNotFoundException{
		List <String> res = new ArrayList<String>();
		
		Model model=ModelFactory.createDefaultModel();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fl = classLoader.getResourceAsStream(rdf);
		model.read(fl,null,"TTL");

		StmtIterator it = model.listStatements();
		while(it.hasNext()) {
			Statement statement = it.next();
			RDFNode o = statement.getObject();
			Property p = statement.getPredicate();

			if (p.toString().equals("http://www.fi.upm.es/linked-data/group13/ontology/nombreInstalacion")) {
				res.add(o.toString());
			}
		}
		return res;
	}
	public static String districtChoice(String rdf, String colegio) throws FileNotFoundException {
		Model model=ModelFactory.createDefaultModel();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fl = classLoader.getResourceAsStream(rdf);
		model.read(fl,null,"TTL");

		StmtIterator it = model.listStatements();
		String obj_res = "";
		while(it.hasNext()) {
			Statement statement = it.next();
			RDFNode o = statement.getObject();
			Property p = statement.getPredicate();
			Resource s = statement.getSubject();

			if (p.toString().equals("http://www.fi.upm.es/linked-data/group13/ontology/nombreInstalacion")) {
				if (o.toString().equals(colegio)) {
					obj_res = s.toString();
				}
			}
		}
		model.close();
		Model model2=ModelFactory.createDefaultModel();
		ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();
		InputStream fl2 = classLoader2.getResourceAsStream(rdf);
		model2.read(fl2,null,"TTL");

		StmtIterator it2 = model2.listStatements();
		String res = "";
		while(it2.hasNext()) {
			Statement statement = it2.next();
			RDFNode o = statement.getObject();
			Property p = statement.getPredicate();
			Resource s = statement.getSubject();
			
			if (s.toString().equals(obj_res)) {
				if (p.toString().equals("http://rdfs.co/juso/Neighborhood")) {
					res = o.toString();
				}
			}
		}
		model2.close();

		return res;
	}
	public static List <String> EventSeeker(String rdf, String distrito) throws FileNotFoundException {
		List <String> list = new ArrayList<String>();

		Model model=ModelFactory.createDefaultModel();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fl = classLoader.getResourceAsStream(rdf);
		model.read(fl,null,"TTL");

		StmtIterator it = model.listStatements();
		while(it.hasNext()) {
			Statement statement = it.next();
			RDFNode o = statement.getObject();
			Property p = statement.getPredicate();
			Resource s = statement.getSubject();

			if (p.toString().equals("http://rdfs.co/juso/Neighborhood")) {
				if (o.toString().equals(distrito)) {
					list.add(s.toString());
				}
			}
		}
		model.close();

		return list;
	}
	public static List <Evento> EventReader(String rdf, List <String> list) throws FileNotFoundException {
		List <Evento> listEvents = new ArrayList<Evento>();

		String p1 = "http://www.fi.upm.es/linked-data/group13/ontology/tituloEvento";
		String p2 = "http://www.fi.upm.es/linked-data/group13/ontology/diasSemanaEvento";
		String p3 = "http://dbpedia.org/ontology/eventDate";
		String p4 = "https://www.ebu.ch/metadata/ontologies/ebucore/index.html#eventEndDate";
		String p5 = "http://www.fi.upm.es/linked-data/group13/ontology/urlEvento";
		String p6 = "http://www.fi.upm.es/linked-data/group13/ontology/urlInstalacion";
		String p7 = "http://www.fi.upm.es/linked-data/group13/ontology/nombreInstalacion";
		
		for (String objeto:list) {
			Evento event = new Evento(objeto);
			
			Model model=ModelFactory.createDefaultModel();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream fl = classLoader.getResourceAsStream(rdf);
			model.read(fl,null,"TTL");

			StmtIterator it = model.listStatements();
			while(it.hasNext()) {
				Statement statement = it.next();
				RDFNode o = statement.getObject();
				Property p = statement.getPredicate();
				Resource s = statement.getSubject();
				if (s.toString().equals(objeto)) {
					if (p.toString().equals(p1)) {
						event.setTitulo(o.toString());
					}
					if (p.toString().equals(p2)) {
						event.setDiasSemana(o.toString());
					}
					if (p.toString().equals(p3)) {
						event.setInicioEvento(o.toString());
					}	
					if (p.toString().equals(p4)) {
						event.setFinEvento(o.toString());
					}
					if (p.toString().equals(p5)) {
						event.setUrlEvento(o.toString());
					}
					if (p.toString().equals(p6)) {
						event.setUrlInstalacion(o.toString());
					}
					if (p.toString().equals(p7)) {
						event.setNombreInstalacion(o.toString());
					}	
				}
			}
			model.close();
			listEvents.add(event);
		}

		return listEvents;
	}
}
