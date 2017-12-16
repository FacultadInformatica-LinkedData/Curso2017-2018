package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
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
		OntClass person = model.getOntClass(ns+"Person");
		
		System.out.println("Person individuals:");
		for (ExtendedIterator<? extends OntResource> it = person.listInstances(); it.hasNext(); ) {
			System.out.println("\t"+it.next().getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> subClassIt = person.listSubClasses();
		System.out.println("Person subclasses:");
		while(subClassIt.hasNext()) {
			System.out.println("\t"+subClassIt.next().getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel inf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass infPerson = inf.getOntClass(ns + "Person");
		System.out.println("Indirect instances:");
		for (ExtendedIterator<? extends OntResource> it = infPerson.listInstances(); it.hasNext(); ) {
			System.out.println("\t"+it.next().getURI());
		}
		
		System.out.println("Indirect subclasses:");
		for (ExtendedIterator<? extends OntResource> it = infPerson.listSubClasses(); it.hasNext(); ) {
			while (it.hasNext()) {
				System.out.println("\t"+it.next().getURI());
			}
		}
	
	}
}
