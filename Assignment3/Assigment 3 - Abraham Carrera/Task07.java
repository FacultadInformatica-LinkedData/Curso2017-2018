package upm.oeg.wsld.jena;

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
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "resources/example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		 ExtendedIterator individuals = model.listIndividuals();
                 OntClass person=model.getOntClass(ns+"Person");
                 
                 System.out.println("Individuals of Person");
                 while(individuals.hasNext()){
                     Individual ind=(Individual)individuals.next();
                     if(ind.hasOntClass(person)){
                         System.out.println(ind.getURI());
                     }
                     
                 }
		// ** TASK 7.2: List all subclasses of "Person" **
		 ExtendedIterator subclasses = person.listSubClasses();
               
                 
                 System.out.println("SubClasses of Person");
                 while(individuals.hasNext()){
                     OntClass subclass=(OntClass)subclasses.next();
                     
                     System.out.println(subclass.getURI());
                     
                     
                 }
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
                Reasoner reasoner=ReasonerRegistry.getRDFSReasoner();
                InfModel inf=ModelFactory.createInfModel(reasoner,model);
                
                String query = "PREFIX rdfs: <" + RDFS.getURI() + ">" +
				" SELECT ?pers " +
				" WHERE { ?pers a <http://somewhere#Person>.} " ;
		Query q = QueryFactory.create(query);
		QueryExecution qe = QueryExecutionFactory.create(q, inf);
		ResultSet results = qe.execSelect();
                   
                System.out.println("Instances of person:");
		while (results.hasNext()) {
			QuerySolution sol = results.nextSolution();
			Resource res = (Resource) sol.get("person");
			System.out.println( res.getURI());
		}
                query = "PREFIX rdfs: <" + RDFS.getURI() + ">" +
				" SELECT ?sub " +
				" WHERE { ?sub rdfs:subClassOf <http://somewhere#Person>.} " ;
                q = QueryFactory.create(query);
                qe = QueryExecutionFactory.create(q, inf);
                results = qe.execSelect();
                
                System.out.println("Subclasses of person:");
		while (results.hasNext()) {
			QuerySolution sol = results.nextSolution();
			Resource res = (Resource) sol.get("person");
			System.out.println( res.getURI());
		}
                
                
	
	}
}