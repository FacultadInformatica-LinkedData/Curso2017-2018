package upm.oeg.wsld.jena;

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
		System.out.println("Task 7.1");
		System.out.println("Individuals of Person");
		System.out.println("\n");
		
		ExtendedIterator instances = model.getOntClass(ns+"Person").listInstances();

		while (instances.hasNext())
		{
			Individual inst = (Individual) instances.next();
			System.out.println("Instance of Person: "+inst.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Task 7.2");
		System.out.println("Subclasses of Person");
		System.out.println("\n");

		ExtendedIterator<OntClass> subclasses = model.getOntClass(ns+"Person").listSubClasses();
		
		while (subclasses.hasNext())
		{
			System.out.println("Subclass of Person: "+subclasses.next().getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("Task 7.3");

		ExtendedIterator<OntClass> listSubclass = model.getOntClass(ns+"Person").listSubClasses();
		ExtendedIterator<Individual> listInstances;

		//Imprimiremos las subclases y las instancias de cada subclase
		while (listSubclass.hasNext()){
	        listInstances = (ExtendedIterator<Individual>)  listSubclass.listInstances();
	        System.out.println("Instances: "+ listSubclass.next().getURI());
	   	
	   		while(listInstances.hasNext()){
	   			System.out.println("Instances: "+ listInstances.next().getURI());
	   		}

			
		}
	
	}
}
