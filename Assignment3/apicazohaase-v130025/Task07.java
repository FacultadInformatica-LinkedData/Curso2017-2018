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
		
		ExtendedIterator<Individual> list = model.listIndividuals();
		OntClass person = model.getOntClass(ns + "Person");
		
		while (list.hasNext()) {
			Individual indiv = list.next();
			System.out.println("|-- Individual: " + ind.getURI());
			
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n** TASK 7.2: List all subclasses of \"Person\"\n");
		ExtendedIterator<OntClass> i = person.listSubClasses();
		while (i.hasNext()) {
			OntClass ind2 = i.next();
			System.out.println("|-- Subclass: " + ind2.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		ExtendedIterator<OntClass> classes = person.listSubClasses();
		ExtendedIterator<Individual> instances = (ExtendedIterator<Individual>) person.listInstances();
		while (classes.hasNext()) {
			OntClass ind3 = classes.next();
			System.out.println("|-- All subclasses: " + ind3.getURI());
		}
		
		while (instances.hasNext()) {
			Individual indivInst = (Individual) instances.next();
			System.out.println("|-- Instances: " + indivInst.getURI());
		}
		
	
	}
}