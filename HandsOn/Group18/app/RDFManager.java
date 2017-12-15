package linkeddata;
import java.util.LinkedList;

import java.io.InputStream;


import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.LiteralImpl;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class RDFManager {
	
	private OntModel model;

	public RDFManager() {
		
		String filename = "C:\\Users\\Alvaro\\Desktop\\UPM\\Semestre7\\WebSemantica\\practica\\buenoGrupo18.rdf";
		
		model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		InputStream in = FileManager.get().open(filename);
		
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
		
		model.read(in, null);
	}

	public LinkedList<RDFResult> searchByName(String name) {
		//Listar todos los parkings que tengan el parametro name en su nombre
		
		LinkedList<RDFResult> nombres = new LinkedList<RDFResult>();
		String queryString =
				
				"SELECT ?x ?y ?z "+
				"WHERE {  ?x ?y ?z  }";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,this.model);
		ResultSet results = qexec.execSelect();
		
		while(results.hasNext()){
			
			QuerySolution binding = results.nextSolution();
			Resource name1 = (Resource) binding.get("x");
			Resource parking = (Resource) binding.get("y");
			RDFNode number = (RDFNode) binding.get("z");
			
			RDFResult element = new RDFResult(name1.getURI(), parking.getURI(), 0);
			nombres.add(element);
		}
		
		return nombres;
	}

	public LinkedList<RDFResult> searchByParkingSpots(int min, int max) {
		//Listar todos los parkings cuya capacidad este entre min y max (incluidos)
		LinkedList<RDFResult> nombres = new LinkedList<RDFResult>();
		String queryString =
				
				"SELECT ?x ?y ?z "+
				"WHERE {  ?x ?y ?z  }";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,this.model);
		ResultSet results = qexec.execSelect();
		
		while(results.hasNext()){
			
			QuerySolution binding = results.nextSolution();
			Resource name1 = (Resource) binding.get("x");
			Resource parking = (Resource) binding.get("y");
			RDFNode number = (RDFNode) binding.get("z");
			System.out.println(name1+" "+number);
			RDFResult element = new RDFResult(name1.getURI(), parking.getURI(), 0);
			nombres.add(element);
		}
		
		return nombres;
	}
}