package main.java.upm.oeg.wsld.jena;

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
 * @author tokalevo
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "resources/example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF); // RDFS_MEM
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator instances = person.listInstances();
		
		while (instances.hasNext())
		{
			Individual inst = (Individual) instances.next();
			System.out.println("Instance of Person: "+inst.getURI());
		}
		
		System.out.println("\n");
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		
		while (subclasses.hasNext())
		{
			OntClass subclass = (OntClass) subclasses.next();
			System.out.println("Subclass of Person: "+subclass.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,model);
		OntClass person2 = model2.getOntClass(ns+"Person");
		ExtendedIterator<OntClass> iterator1 = person2.listSubClasses();
		ExtendedIterator<Individual> iterator2 = model2.listIndividuals(persona);
		while (iterator1.hasNext()) {
			OntClass aux = iterator1.next();
			System.out.println("|-- Subclasses: " + aux.getURI());
		}
		
		while (iterator2.hasNext()) {
			Individual indiv_instance = (Individual) iterator2.next();
			System.out.println("|-- Instances: " + indiv_instance.getURI());
		}
	
	}
}