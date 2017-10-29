package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

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
		String filename = "example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		System.out.println("Task 7.1: List all individuals of \"Person\"");
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> it1 = model.listIndividuals(person);
		while(it1.hasNext()){
			Individual ind = it1.next();
			System.out.println(ind.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Task 7.2: List all subclasses of \"Person\"");
		ExtendedIterator<OntClass> it2 = person.listSubClasses();
		
		while(iterator2.hasNext()){
			System.out.println(it2.next());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("Task 7.3: List indirect instances.");
		OntModel inferenceM = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,model);
		OntClass inferenceP = inferenceM.getOntClass(ns+"Person");
		
		ExtendedIterator<Individual> inferenceIt = inferenceM.listIndividuals(inferenceP);
		ExtendedIterator<OntClass> inferenceClIt = inferenceP.listSubClasses();
		
		while(inferenceIt.hasNext()){
			Individual inf = inferenceIt.next();
			System.out.println(inf.getURI());
		}
		while(inferenceClIt.hasNext()){
			System.out.println(inferenceClIt.next());
		}
		
	}
}